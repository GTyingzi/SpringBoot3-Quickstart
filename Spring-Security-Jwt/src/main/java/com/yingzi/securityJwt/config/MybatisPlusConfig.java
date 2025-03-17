package com.yingzi.securityJwt.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yingzi
 * @date 2025/3/11:17:46
 */
@Configuration
@MapperScan("com.yingzi.securityJwt.dao")
public class MybatisPlusConfig {

}
