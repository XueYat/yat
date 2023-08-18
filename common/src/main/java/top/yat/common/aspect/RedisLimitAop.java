package top.yat.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import top.yat.common.annotation.RedisLimit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static top.yat.common.exception.enums.GlobalErrorCodeConstants.REDIS_LIMIT;
import static top.yat.common.exception.util.ServiceExceptionUtil.exception;

@Slf4j
@Aspect
@Component
public class RedisLimitAop {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private DefaultRedisScript<Long> redisScript;

    @PostConstruct
    public void init() {
        redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/RateLimiter.lua")));
    }

    @Pointcut("@annotation(top.yat.common.annotation.RedisLimit)")
    private void check() {

    }

    @Before("check()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //拿到RedisLimit注解，如果存在则说明需要限流
        RedisLimit redisLimit = method.getAnnotation(RedisLimit.class);
        if (redisLimit != null) {
            //获取redis的key
            String key = redisLimit.key();
            String className = method.getDeclaringClass().getName();
            String name = method.getName();
            String limitKey = key + ":" + className + ":" + name;
            long limit = redisLimit.permitsPerSecond();
            long expire = redisLimit.expire();
            List<String> keys = new ArrayList<>();
            keys.add(limitKey);
            String luaScript = buildLuaScript();
            RedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript, Long.class);
            Long count = stringRedisTemplate.execute(redisScript, keys, String.valueOf(limit), String.valueOf(expire));
            log.info("Access try count is {} for key={}", count, limitKey);
            if (count != null && count == 0) {
                log.info("令牌桶={}，获取令牌失败", limitKey);
                throw exception(REDIS_LIMIT);
            }
        }
    }

    /**
     * 构建redis lua脚本
     */
    private String buildLuaScript() {
        // 两种方式：1.硬编码编写lua脚本；2.生成一个lua文件放在resources目录下，利用@PostConstruct注解提前加载
        /*StringBuilder luaString = new StringBuilder();
        luaString.append("local key = KEYS[1]");
        //获取ARGV内参数Limit
        luaString.append("\nlocal limit = tonumber(ARGV[1])");
        //获取key的次数
        luaString.append("\nlocal curentLimit = tonumber(redis.call('get', key) or "0")");
        luaString.append("\nif curentLimit + 1 > limit then");
        luaString.append("\nreturn 0");
        luaString.append("\nelse");
        //自增长 1
        luaString.append("\n redis.call('INCRBY', key, 1)");
        //设置过期时间
        luaString.append("\nredis.call('EXPIRE', key, ARGV[2])");
        luaString.append("\nreturn curentLimit + 1");
        luaString.append("\nend");*/
        return redisScript.getScriptAsString();
    }
}
