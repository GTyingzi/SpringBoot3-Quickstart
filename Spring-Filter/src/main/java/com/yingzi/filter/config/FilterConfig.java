package com.yingzi.filter.config;

import com.yingzi.filter.component.FilterOne;
import com.yingzi.filter.component.FilterTwo;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yingzi
 * @date 2025/3/10:20:28
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registerFilterOne() {
        FilterRegistrationBean<FilterOne> bean = new FilterRegistrationBean<>();
        bean.setOrder(1);
        bean.setFilter(new FilterOne());
        bean.addUrlPatterns("/echo/hello");
        return bean;
    }

    @Bean
    public FilterRegistrationBean registerMyAnotherFilter(){
        FilterRegistrationBean<FilterTwo> bean = new FilterRegistrationBean<>();
        bean.setOrder(2);
        bean.setFilter(new FilterTwo());
        // 匹配所有url
        bean.addUrlPatterns("/*");
        return bean;
    }
}
