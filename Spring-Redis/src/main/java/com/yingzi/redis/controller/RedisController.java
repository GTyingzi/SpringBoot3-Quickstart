package com.yingzi.redis.controller;

import com.yingzi.redis.model.entity.PmsBrand;
import com.yingzi.redis.service.PmsBrandService;
import com.yingzi.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yingzi
 * @date 2025/3/11:19:09
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private PmsBrandService brandService;

    @PostMapping("/simpleTest")
    PmsBrand simpleTest(){
        PmsBrand pmsBrand = brandService.findById(2L);

        // 设置缓存key
        String key = "redis:simple:" + pmsBrand.getId();
        redisService.set(key, pmsBrand);
        return (PmsBrand) redisService.get(key);
    }

}
