package top.yat.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解使用Redis进行接口限流
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface RedisLimit {

    /**
     * 资源的key,唯一
     * 作用：不同的接口，不同的流量控制
     */
    String key() default "yat:redis-limit:";

    /**
     * 最多的访问限制次数
     */
    long permitsPerSecond() default 2;

    /**
     * 过期时间也可以理解为单位时间，单位秒，默认60
     */
    long expire() default 60;

}
