package com.yingzi.schedule.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author yingzi
 * @date 2025/3/13:12:30
 */
@Slf4j
@EnableScheduling
@Component
public class ScheduleTask {


    /**
     * 每分钟执行一次
     */
    @Scheduled(cron = "0 0/1 * * * *")
    public void printTask() {
        log.info("ScheduleTask定时任务执行，当前时间：{}", LocalDateTime.now());
    }
}
