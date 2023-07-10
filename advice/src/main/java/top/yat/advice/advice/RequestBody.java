package top.yat.advice.advice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import top.yat.advice.service.WhiteService;
import top.yat.common.utils.EncryptUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/**
 * @author XueYat
 * @date 2023/07/07
 * 拦截请求（可以对参数解密等操作）
 * RequestBodyAdvice仅对使用了@RequestBody注解的生效，因为它原理上还是AOP，所以GET方法是不会操作的
 */
@ControllerAdvice
@Slf4j
public class RequestBody implements RequestBodyAdvice {

    /**
     * 是否对请求拦截处理(根据实际情况可以写在配置文件中)
     */
    private final static Boolean ENCRPYT_ENABLE = true;

    /**
     * 密钥(根据实际情况可以写在配置文件中)
     */
    private final static String ENCRYPT_KEY = "abdel99999999";

    @Resource
    WhiteService whiteService;

    @Cacheable(value = "whiteList")
    public Set whiteList() {
        log.info("白名单缓存");
        Set set = new HashSet();
        set.add("/test/postQuery");
        set.add("/test/select");
        return set;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        // 全局控制，是否开启拦截
        if (!ENCRPYT_ENABLE.equals(true)) {
            return false;
        }
        // 根据入参header中的encrpyt参数进行判断当前接口是否需要解密
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // 白名单放行
        String path = request.getRequestURI();
        Set whiteList = whiteService.whiteList();
        boolean contains = whiteList.contains(path);
        log.info("当前接口: {}，是否放行：{}", path, contains);
        if (contains) {
            return false;
        }

        String encrpyt = request.getHeader("encrpyt");
        if ("0".equals(encrpyt)) {
            return false;
        }
        // methodParameter.hasMethodAnnotation(GetMapping.class);
        String method = request.getMethod();
        // 排除get请求
        return !method.equals(RequestMethod.GET.toString());
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        String requestBodyStr = getRequestBodyStr(httpInputMessage.getBody());
        requestBodyStr = requestBodyStr.replaceAll("\\r\\n", "");
        log.info("RequestBody-beforeBodyRead，请求参数为: {}", requestBodyStr);
        String s = null;
        try {
            s = EncryptUtils.decryptDES(requestBodyStr, ENCRYPT_KEY);
            log.info("RequestBody-beforeBodyRead-解密之后为: {}", s);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("RequestBody-beforeBodyRead-解密处理异常", e);
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
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }


    /**
     * request body流数据转换为String
     *
     * @param inputStream 输入流
     * @return String
     * @throws IOException 流异常
     */
    public static String getRequestBodyStr(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(inputStream)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] charBuffer = new char[128];
            int bytesRead;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                builder.append(charBuffer, 0, bytesRead);
            }
        }
        return builder.toString();
    }
}
