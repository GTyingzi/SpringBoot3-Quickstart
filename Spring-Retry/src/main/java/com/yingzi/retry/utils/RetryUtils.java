package com.yingzi.retry.utils;

import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author yingzi
 * @date 2025/3/15:22:05
 */
public class RetryUtils {

    /**
     * 设置固定退避策略
     * @return
     */
    public static BackOffPolicy getFixedBackOffPolicy(long backOffPeriod) {
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        // 设置固定的延迟时间
        fixedBackOffPolicy.setBackOffPeriod(backOffPeriod);
        return fixedBackOffPolicy;
    }

    /**
     * 固定次数重试策略，默认重试最大次数为3次，RetryTemplate默认使用的策略
     * @return
     */
    public static RetryPolicy getSimpleRetryPolicy(int maxAttempts) {
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(maxAttempts);
        return simpleRetryPolicy;
    }

    public static RetryTemplate getRetryTemplate(int maxAttempts, long backOffPeriod) {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(getSimpleRetryPolicy(maxAttempts));
        retryTemplate.setBackOffPolicy(getFixedBackOffPolicy(backOffPeriod));
        return retryTemplate;
    }
}
