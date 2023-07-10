package top.yat.advice.advice;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.yat.common.utils.EncryptUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author XueYat
 * @date 2023/07/07
 */
@RestControllerAdvice
@Slf4j
public class ResponseBody implements ResponseBodyAdvice {

    /**
     * 是否对响应拦截处理
     */
    private final static Boolean ENCRPYT_ENABLE = true;

    /**
     * 密钥(根据实际情况可以写在配置文件中)
     */
    private final static String ENCRYPT_KEY = "abdel99999999";

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        // 是否开启拦截
        if (!ENCRPYT_ENABLE.equals(true)) {
            return false;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String encrpyt = request.getHeader("encrpyt");
        if ("0".equals(encrpyt)) {
            return false;
        }
        String method = request.getMethod();
        methodParameter.hasMethodAnnotation(GetMapping.class);
        // 排除get请求
        return !method.equals(RequestMethod.GET.toString());
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info("ResponseBody-beforeBodyWrite，响应参数为:{}", JSON.toJSONString(o));
        if (o == null) {
            return null;
        }
        String str;
        try {
            if (o instanceof String) {
                str = (String) o;
            } else {
                str = JSON.toJSONString(o);
            }
            String s = EncryptUtils.encryptDES(str, ENCRYPT_KEY);
            log.info("ResponseBody-beforeBodyWrite-加密之后为: {}", s);
            return s.replaceAll("\\r\\n", "").replaceAll("\\n", "").replaceAll("\\r", "");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ResponseBody-beforeBodyWrite-加密处理异常", e);
            return "";
        }
    }
}
