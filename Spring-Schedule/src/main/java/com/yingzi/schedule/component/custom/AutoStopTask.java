package com.yingzi.schedule.component.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.Objects;

/**
 * @author yingzi
 * @date 2025/3/13:18:16
 */
@Component
@Slf4j
public class AutoStopTask {

    private final CustomTaskScheduler customTaskScheduler;

    private int count;

    public AutoStopTask(CustomTaskScheduler customTaskScheduler) {
        this.customTaskScheduler = customTaskScheduler;
    }


    @Scheduled(cron = "0 0/1 * * * *")
    public void printTask() {
        log.info("AutoStopTask，任务执行次数：{}", count + 1);

        count++;

        // 执行3次后自动停止
        if (count >= 3) {
            log.info("AutoStopTask 任务已执行指定次数，现在自动停止");
            boolean cancelled = customTaskScheduler.getScheduledTasks().get(this).cancel(true);
            if (cancelled) {
                count = 0;
                ScheduledMethodRunnable runnable = new ScheduledMethodRunnable(this, Objects.requireNonNull(ReflectionUtils.findMethod(this.getClass(), "printTask")));
                customTaskScheduler.schedule(runnable, new CronTrigger("0 0/1 * * * *"));
            }
        }
    }
}
