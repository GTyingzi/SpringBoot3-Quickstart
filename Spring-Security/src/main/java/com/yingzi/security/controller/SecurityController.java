package com.yingzi.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author yingzi
 * @date 2025/3/16:17:09
 */
@RestController
@RequestMapping("/security")
public class SecurityController {

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/admin")
    public String admin() {
        return "欢迎进入admin页面";
    }

    @GetMapping("/user")
    public String user() {
        return "欢迎进入user页面";
    }

    @PreAuthorize("hasRole('ADMIN')") // 只有ADMIN角色才能访问 /security/admin/dashboard
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "欢迎进入admin/dashboard页面";
    }

    @Secured("ROLE_USER") // 只有 USER 角色才能访问 /security/user/dashboard
    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "欢迎进入user/dashboard页面";
    }

    @PreAuthorize("hasRole('ADMIN') or #username == authentication.name") // 只有 ADMIN 角色或当前登录用户与请求的 username 一致才能访问 /security/user/info
    @GetMapping("/adminOrUsername/info")
    public String getUserInfo(@RequestParam("username") String username) {
        // 当前登录用户是 ADMIN，或者请求的 username 与登录用户一致时允许访问
        return "User Info: " + username;
    }

}
