package com.yingzi.aop.component;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author yingzi
 * @date 2025/3/10:19:29
 */
@Aspect
@Component
public class PerformanceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceAspect.class);

    @Pointcut("execution(* com.yingzi.aop.controller.*.*(..))")
    public void performance() {
    }

    @Around("performance()")
    public Object measurePerformance(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        long startTime = System.currentTimeMillis();
        LOGGER.info("Start executing method: {}", methodName);

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        LOGGER.info("Finish executing method: {}. Execution time: {} ms", methodName, executionTime);
        return result;
    }
}

