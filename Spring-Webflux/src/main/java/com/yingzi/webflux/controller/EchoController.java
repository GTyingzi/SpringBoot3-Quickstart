package com.yingzi.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author yingzi
 * @date 2025/4/7:14:19
 */
@RestController
@RequestMapping("/echo")
public class EchoController {

    @GetMapping("hi")
    public Mono<String> hi() {
        return Mono.just("hi");
    }
}
