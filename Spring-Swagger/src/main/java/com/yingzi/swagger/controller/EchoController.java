package com.yingzi.swagger.controller;

import com.yingzi.swagger.api.EchoAPI;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yingzi
 * @date 2025/3/10:14:48
 */
@RestController
public class EchoController implements EchoAPI {
    @Override
    public String echo(String message) {
        return "hello, " + message;
    }
}
