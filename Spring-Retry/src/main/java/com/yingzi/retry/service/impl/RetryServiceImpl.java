package com.yingzi.retry.service.impl;

import com.yingzi.retry.service.RetryService;
import com.yingzi.retry.utils.RetryUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetryServiceImpl implements RetryService {

    @Retryable(
            retryFor = {RuntimeException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000),
            recover = "callApiRecover2"
    )
    @Override
    public void callApi(int i) {
        log.info("开始执行第{}次业务",i);

        // 模拟调用第三方接口失败的场景
        int random = (int) (Math.random() * 10);
        if (random < 7) {
            log.error("第{}次,调用第三方接口失败", i);
            throw new RuntimeException("调用第三方接口失败");
        }
        log.info("第{}次业务执行完毕",i);

    }

    @Recover
    public void callApiRecover(RuntimeException e, int i) {
        log.error("第{}次业务执行失败，走callApiRecover逻辑",i);
    }

    @Recover
    public void callApiRecover2(RuntimeException e, int i) {
        log.error("第{}次业务执行失败，走callApiRecover2逻辑",i);
    }

    public void baseCallApi(int i) {
        log.info("开始执行第{}次业务",i);

        // 模拟调用第三方接口失败的场景
        int random = (int) (Math.random() * 10);
        if (random < 7) {
            log.error("第{}次,调用第三方接口失败", i);
            throw new RuntimeException("调用第三方接口失败");
        }
        log.info("第{}次业务执行完毕",i);

    }

    @Override
    public void callApiRetryTemplate(int i) {
        RetryUtils.getRetryTemplate(1, 1000L).execute(
                retryContext -> {
                    baseCallApi(i);
                    return null;
                },
                (RecoveryCallback<Void>) retryContext -> {
                    callApiRetryTemplateRecover(i);
                    return null;
                }
        );
    }

    private void callApiRetryTemplateRecover(int i) {
        log.error("第{}次业务执行失败，走callApiRetryTemplateRecover逻辑",i);
    }

}
