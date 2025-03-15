package com.yingzi.retry.controller;

import com.yingzi.retry.service.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yingzi
 * @date 2025/3/15:20:58
 */
@RestController
@RequestMapping("/retry")
public class RetryController {

    @Autowired
    private RetryService retryService;

    @GetMapping("/callApi")
    public void callApi() {
        for (int i = 0; i < 10; i++) {
            retryService.callApi(i);
        }
    }

    @GetMapping("/callApi-RetryTemplate")
    public void callApiRetryTemplate() {
        for (int i = 0; i < 10; i++) {
            retryService.callApiRetryTemplate(i);
        }
    }
}
