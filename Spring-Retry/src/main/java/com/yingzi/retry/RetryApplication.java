package com.yingzi.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author yingzi
 * @date 2025/3/15:20:44
 */
@EnableRetry
@SpringBootApplication
public class RetryApplication {
    public static void main(String[] args) {
        SpringApplication.run(RetryApplication.class, args);
    }
}
