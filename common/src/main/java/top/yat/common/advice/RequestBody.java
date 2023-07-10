package top.yat.common.advice;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import top.yat.common.utils.EncryptUtils;

import java.io.*;
import java.lang.reflect.Type;

/**
 * 拦截请求（可以对参数解密等操作）
 * RequestBodyAdvice仅对使用了@RqestBody注解的生效,因为它原理上还是AOP ,所以GET方法是不会操作的
 */
@ControllerAdvice
@Slf4j
public class RequestBody implements RequestBodyAdvice {

    //是否对请求拦截处理(根据实际情况可以写在配置文件中)
    private Boolean enable = true;

    //密钥(根据实际情况可以写在配置文件中)
    private final static String encryptKey = "abdel99999999";

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        log.info("【进入-RequestBody-supports】");
        //是否开启拦截
        if (!enable.equals(true)) {
            return false;
        }
        //排除get请求
        if (methodParameter.hasMethodAnnotation(GetMapping.class)) {
            return false;
        }
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        log.info("【进入-RequestBody-beforeBodyRead】");
        log.info("【拦截到的请求参数为:】" + httpInputMessage.toString());

        String s = null;
        String requestBodyStr = getRequestBodyStr(httpInputMessage.getBody());
        requestBodyStr = requestBodyStr.replaceAll("\\r\\n", "");
        JSONObject jsonObject = JSONObject.parseObject(requestBodyStr);
        String inputStr = jsonObject.getString("inputStr");
        try {
            s = EncryptUtils.decryptDES(inputStr, encryptKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用解密后的数据，构造新的读取流
        InputStream rawInputStream = new ByteArrayInputStream(s.getBytes());
        return new HttpInputMessage() {
            @Override
            public HttpHeaders getHeaders() {
                return httpInputMessage.getHeaders();
            }

            @Override
            public InputStream getBody() throws IOException {
                return rawInputStream;
            }
        };
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        log.info("【进入-RequestBody-afterBodyRead】");
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        log.info("【进入-RequestBody-handleEmptyBody】");
        return null;
    }


    /**
     * reuqest body流数据转换为String
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String getRequestBodyStr(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(inputStream)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                builder.append(charBuffer, 0, bytesRead);
            }
        } else {
            builder.append("");
        }
        return builder.toString();

    }
}
