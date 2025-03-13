package com.yingzi.crudMybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yingzi
 * @date 2025/3/10:21:14
 */
@Configuration
@MapperScan("com.yingzi.crudMybatis.dao")
public class MyBatisConfig {
}
