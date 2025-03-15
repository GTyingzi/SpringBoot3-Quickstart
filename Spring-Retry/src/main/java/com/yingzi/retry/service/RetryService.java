package com.yingzi.retry.service;

/**
 * @author yingzi
 * @date 2025/3/15:20:58
 */
public interface RetryService {

    void callApi(int i);

    void callApiRetryTemplate(int i);
}
