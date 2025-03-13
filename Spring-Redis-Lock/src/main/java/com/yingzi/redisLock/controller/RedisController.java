package com.yingzi.redisLock.controller;

import com.yingzi.redisLock.service.RedisLockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author yingzi
 * @date 2025/3/12:14:48
 */
@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisController {

    @Autowired
    private RedisLockService redisLockService;

    @GetMapping("/lock")
    public String lock(@RequestParam("key") String key) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                redisLockService.lock(key);
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                redisLockService.unlock(key);
            }
            ).start();
        }
        return "OK";
    }

}
