package com.yingzi.async.component;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.Future;

/**
 * @author yingzi
 * @date 2025/3/10:19:52
 */
@Component
@Slf4j
public class AsyncJob {

    @Async("tp1")
    public void job1() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        Thread.sleep(2000);
        long endTime = System.currentTimeMillis();
        log.info("job1任务耗时：{}ms", endTime - beginTime);
    }

    @Async("tp2")
    public void job2() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        Thread.sleep(2000);
        long endTime = System.currentTimeMillis();
        log.info("job2任务耗时：{}ms", endTime - beginTime);
    }

    @Async
    public void job3() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        Thread.sleep(2000);
        long endTime = System.currentTimeMillis();
        log.info("job3任务耗时：{}ms", endTime - beginTime);
    }

    @Async
    public Future<String> job1Future() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        Thread.sleep(2000);
        long endTime = System.currentTimeMillis();
        log.info("job1Future任务耗时：{}ms", endTime - beginTime);
        return new AsyncResult<>("job1Future任务完成");
    }

    @Async
    public Future<String> job2Future() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        Thread.sleep(2000);
        long endTime = System.currentTimeMillis();
        log.info("job2Future任务耗时：{}ms", endTime - beginTime);
        return new AsyncResult<>("job2Future任务完成");
    }

    @Async
    public Future<String> job3Future() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        Thread.sleep(2000);
        long endTime = System.currentTimeMillis();
        log.info("job3Future任务耗时：{}ms", endTime - beginTime);
        return new AsyncResult<>("job3Future任务完成");
    }

    @Async
    public Future<String> job1info() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        Thread.sleep(2000);
        long endTime = System.currentTimeMillis();
        log.info("job1info任务耗时：{}ms", endTime - beginTime);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("当前线程为 {}，请求方法为 {}，请求路径为：{}", Thread.currentThread().getName(), request.getMethod(), request.getRequestURL().toString());
        return new AsyncResult<>("job1info任务完成");
    }

    @Async
    public Future<String> job2info() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        Thread.sleep(2000);
        long endTime = System.currentTimeMillis();
        log.info("job2info任务耗时：{}ms", endTime - beginTime);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("当前线程为 {}，请求方法为 {}，请求路径为：{}", Thread.currentThread().getName(), request.getMethod(), request.getRequestURL().toString());
        return new AsyncResult<>("job2info任务完成");
    }

    @Async
    public Future<String> job3info() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        Thread.sleep(2000);
        long endTime = System.currentTimeMillis();
        log.info("job3info任务耗时：{}ms", endTime - beginTime);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("当前线程为 {}，请求方法为 {}，请求路径为：{}", Thread.currentThread().getName(), request.getMethod(), request.getRequestURL().toString());
        return new AsyncResult<>("job3info任务完成");
    }
}

