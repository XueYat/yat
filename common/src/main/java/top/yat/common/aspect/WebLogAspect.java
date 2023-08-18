package top.yat.common.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.yat.common.utils.IpAddressUtil;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Order(0)
@Component
public class WebLogAspect {

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    @Pointcut("execution(public * top.yat..*Controller.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void before(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 记录请求内容
        THREAD_LOCAL.set(JSON.toJSONString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "obj", pointcut = "webLog()")
    public void afterReturning(Object obj) {
        // 处理完请求，返回内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        log.info("Request Url: [{}], Args: [{}], IP: [{}], SYSTEM: [{}], Spend Time: [{}ms], Response: [{}]",
                request.getRequestURI(), THREAD_LOCAL.get(), IpAddressUtil.getIpAddr(request), IpAddressUtil.getOs(request),
                System.currentTimeMillis() - startTime.get(), JSON.toJSONString(obj));
    }

}
