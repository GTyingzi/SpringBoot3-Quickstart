package com.yingzi.securityJwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yingzi
 * @date 2025/3/17:15:40
 */
@RestController
@RequestMapping("/echo")
public class EchoController {

    @RequestMapping("/hi")
    public String hi() {
        return "hi";
    }
}
