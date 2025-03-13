package com.yingzi.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yingzi
 * @date 2025/3/10:19:29
 */
@RestController
@RequestMapping("/echo")
public class EchoController {

    @GetMapping("hello")
    public String Hello(){
        return "hello";
    }

    @GetMapping("hi")
    public String Hi(){
        return "hi";
    }
}