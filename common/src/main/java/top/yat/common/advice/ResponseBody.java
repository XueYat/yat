package top.yat.common.advice;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.yat.common.utils.EncryptUtils;

/**
 * @author XueYat
 * @date 2023/07/07
 */
@RestControllerAdvice
@Slf4j
public class ResponseBody implements ResponseBodyAdvice {

    //是否对返回拦截处理
    private Boolean enable = false;

    private final static String encryptKey = "abdel99999999";

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //log.info("【进入-ResponseBody-supports】");
        if (enable.equals(true)) {
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //log.info("【进入-ResponseBody-beforeBodyWrite】");
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
            String s = EncryptUtils.encryptDES(str, encryptKey);
            log.info("【加密之后的数据:】" + s);
            return s.replaceAll("\\r\\n", "").replaceAll("\\n", "").replaceAll("\\r", "");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("【ResponseBody加密处理异常】");
            return "";
        }
    }
}
