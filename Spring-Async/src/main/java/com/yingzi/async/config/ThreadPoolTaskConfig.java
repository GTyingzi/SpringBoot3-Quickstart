package com.yingzi.async.config;

import com.yingzi.async.component.ContextDecorator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yingzi
 * @date 2025/3/10:19:50
 */
@Slf4j
@EnableAsync
@Configuration
public class ThreadPoolTaskConfig implements AsyncConfigurer {

    /**
     * 核心线程数（默认线程数）
     */
    private static final int CORE_POOL_SIZE = 5;
    /**
     * 最大线程数
     */
    private static final int MAX_POOL_SIZE = 10;
    /**
     * 允许线程空闲时间（单位：默认为秒）
     */
    private static final int KEEP_ALIVE_TIME = 10;
    /**
     * 缓冲队列大小
     */
    private static final int QUEUE_CAPACITY = 200;
    /**
     * 线程池名前缀
     */
    private static final String THREAD_NAME_PREFIX1 = "Service1-";
    private static final String THREAD_NAME_PREFIX2 = "Service2-";
    private static final String THREAD_DEFALUET_PREFIX = "Default-Service-";


    @Bean("tp1")
    public ThreadPoolTaskExecutor taskExecutor1() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(KEEP_ALIVE_TIME);
        executor.setKeepAliveSeconds(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX1);

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Bean("tp2")
    public ThreadPoolTaskExecutor taskExecutor2() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(KEEP_ALIVE_TIME);
        executor.setKeepAliveSeconds(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX2);

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Bean("default")
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(KEEP_ALIVE_TIME);
        executor.setKeepAliveSeconds(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(THREAD_DEFALUET_PREFIX);

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 增加 TaskDecorator 属性的配置
        executor.setTaskDecorator(new ContextDecorator());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * 指定默认线程池
     */
    @Override
    public Executor getAsyncExecutor() {
        return executor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> log.error("线程池执行任务发横未知错误,执行方法：{}", method.getName(), ex);
    }
}
