package com.yingzi.swagger.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yingzi
 * @date 2025/3/10:14:46
 */
@Tag(name = "Swagger测试接口")
@RequestMapping("/swagger")
public interface EchoAPI {

    @Operation(summary = "测试echo")
    @GetMapping("/echo")
    String echo(String message);
}
