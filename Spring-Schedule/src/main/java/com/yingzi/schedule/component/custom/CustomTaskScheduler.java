package com.yingzi.schedule.component.custom;

import lombok.Getter;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Component;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * @author yingzi
 * @date 2025/3/13:18:15
 */
@Getter
@Component
public class CustomTaskScheduler extends ThreadPoolTaskScheduler {

    private final Map<Object, ScheduledFuture<?>> scheduledTasks = new IdentityHashMap<>();

    @Override
    public ScheduledFuture<?> schedule(Runnable task, Trigger trigger) {
        ScheduledFuture<?> future = super.schedule(task, trigger);
        this.putScheduledTasks(task, future);
        return future;
    }

    private void putScheduledTasks(Runnable task, ScheduledFuture<?> future) {
        ScheduledMethodRunnable runnable = (ScheduledMethodRunnable) task;
        scheduledTasks.put(runnable.getTarget(), future);
    }


}
