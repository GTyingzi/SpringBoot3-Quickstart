package com.yingzi.interceptor.controller;

import com.yingzi.interceptor.annotation.NoNeedToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yingzi
 * @date 2025/3/10:20:12
 */
@RestController
@RequestMapping("/echo")
public class EchoController {

    @NoNeedToken
    @GetMapping("hello")
    public String Hello(){
        return "hello";
    }

    @GetMapping("hi")
    public String Hi(){
        return "hi";
    }
}
