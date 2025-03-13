package com.yingzi.schedule.component;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yingzi
 * @date 2025/3/13:12:38
 */
@Component
@Slf4j
public class ScheduleServiceTask {

    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    @PostConstruct
    public void scheduleTask() {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.info("ScheduleServiceTask定时任务执行，当前时间：{}", LocalDateTime.now());
        }, 0, 1, TimeUnit.MINUTES);
    }
}
