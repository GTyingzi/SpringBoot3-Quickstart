package com.yingzi.security.config;

import com.yingzi.security.service.impl.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author yingzi
 * @date 2025/3/16:19:30
 */
// 用数据库版进行权限校验
@EnableWebSecurity
@Configuration
public class SecurityDBConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityDBConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 页面权限设置
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/security/index").permitAll() // 该路径下的所有请求无需认证
                                .requestMatchers("/user-info/list/**").permitAll()    // 方便测试，/user-info/**路径下无需认证
                                .requestMatchers("/user-info/role-rule").hasRole("超级管理员") // 只有 ADMIN 角色才能访问 /user-info/role-rule
                                .anyRequest().authenticated() // 其他路径下的所有请求都需要认证
                ).userDetailsService(userDetailsService)
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/security/login") // 登陆页
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutSuccessUrl("/security/index") // 注销后重定向到指定路径
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // 可通过GET请求访问注销
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // 使用 BCrypt 进行密码加密
    }

}
