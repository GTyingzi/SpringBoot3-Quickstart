package com.yingzi.aop;

import com.yingzi.aop.component.PerformanceAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author yingzi
 * @date 2025/3/10:19:27
 */
@SpringBootApplication
public class AOPApplication {

    public static void main(String[] args) {
        SpringApplication.run(AOPApplication.class, args);
    }

    @Bean
    public PerformanceAspect performanceAspect() {
        return new PerformanceAspect();
    }
}
