package com.yingzi.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author yingzi
 * @date 2025/3/16:16:09
 */
// 自定义版
//@EnableWebSecurity
//@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 页面权限设置
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/security/index").permitAll() // 该路径下的所有请求无需认证
                                .requestMatchers("/security/admin").hasRole("ADMIN") // 只有 ADMIN 角色才能访问 /security/admin
                                .requestMatchers("/security/user").hasRole("USER") // 只有 USER 角色才能访问 /security/user
                                .anyRequest().authenticated() // 其他路径下的所有请求都需要认证
                )
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
        // 页面安全设置
        http.
                // 禁用 CSRF 防护
                csrf(AbstractHttpConfigurer::disable)
                // 内容安全策略，防止外部来源的脚本加载，减少XSS攻击
                .headers(headers -> headers.contentSecurityPolicy(contentSecurityPolicyConfig -> contentSecurityPolicyConfig.policyDirectives("script-src 'self'")))
                // 安全头部配置
                .headers(headers -> headers
                        .xssProtection(Customizer.withDefaults())   // 防范XSS
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin) // 防范点击劫持
                        .contentTypeOptions(Customizer.withDefaults())  // 禁止内容嗅探
                        .httpStrictTransportSecurity(Customizer.withDefaults()));  // 强制 HTTPS 连接
                ;
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // 创建用户
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user1"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin1"))
                .roles("ADMIN")
                .build();

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(user);
        userDetailsManager.createUser(admin);

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // 使用 BCrypt 进行密码加密
    }
}