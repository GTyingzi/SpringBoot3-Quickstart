package com.yingzi.security.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yingzi
 * @date 2025/3/11:17:46
 */
@Configuration
@MapperScan("com.yingzi.security.dao")
public class MybatisPlusConfig {

}
