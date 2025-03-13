package com.yingzi.async.controller;

import com.yingzi.async.component.AsyncJob;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author yingzi
 * @date 2025/3/10:19:53
 */
@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private AsyncJob asyncJob;

    /**
     * 异步任务
     */
    @RequestMapping("/job")
    public String job() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        asyncJob.job1();
        asyncJob.job2();
        asyncJob.job3();
        long endTime = System.currentTimeMillis();
        log.info("job总耗时：{}ms", endTime - beginTime);
        return "job总耗时：" + (endTime - beginTime) + "ms";
    }

    /**
     * 异步任务Future
     */
    @RequestMapping("/job-future")
    public String jobFuture() throws InterruptedException, ExecutionException {
        long beginTime = System.currentTimeMillis();
        Future<String> r1 = asyncJob.job1Future();
        Future<String> r2 = asyncJob.job2Future();
        Future<String> r3 = asyncJob.job3Future();
        while (!r1.isDone() || !r2.isDone() || !r3.isDone()) {
            // 三个任务都完成
            // 睡眠1s
            Thread.sleep(1000);
        }
        long endTime = System.currentTimeMillis();
        log.info("job总耗时：{}ms", endTime - beginTime);
        log.info("job1任务结果：{}", r1.get());
        return "job总耗时：" + (endTime - beginTime) + "ms";
    }

    /**
     * 异步调用中传递上下文信息
     */
    @RequestMapping("/job-info")
    public String jobInfo() throws InterruptedException {
        Future<String> r1 = asyncJob.job1info();
        Future<String> r2 = asyncJob.job2info();
        Future<String> r3 = asyncJob.job3info();

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("当前线程为 {}，请求方法为 {}，请求路径为：{}", Thread.currentThread().getName(), request.getMethod(), request.getRequestURL().toString());
        while (!r1.isDone() || !r2.isDone() || !r3.isDone()) {
            // 三个任务都完成
            // 睡眠1s
            Thread.sleep(1000);
        }

        return "OK";
    }
}
