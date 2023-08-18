package top.yat.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class ThreadPoolConfig {

    @Bean("YatExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        MyThreadPoolTaskExecutor executor = new MyThreadPoolTaskExecutor();
        // 核心线程数50：线程池创建时候初始化的线程数
        executor.setCorePoolSize(50);
        // 最大线程数200：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(200);
        // 缓冲队列1000：用来缓冲执行任务的队列
        executor.setQueueCapacity(1000);
        // 允许线程的空闲时间300秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(300);
        // 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        executor.setThreadNamePrefix("async-yat-executor");
        executor.initialize();
        return executor;
    }

}
