package com.yingzi.securityJwt.controller;

import com.yingzi.securityJwt.component.JwtUtil;
import com.yingzi.securityJwt.model.entity.UmsAdmin;
import com.yingzi.securityJwt.model.entity.UmsResource;
import com.yingzi.securityJwt.model.req.UserLoginReq;
import com.yingzi.securityJwt.service.UmsAdminService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yingzi
 * @date 2025/3/17:14:08
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private JwtUtil jwtUtil;
    @Autowired
    private UmsAdminService umsAdminService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserLoginReq req, HttpServletResponse response) {
        String username = req.getUsername();
        String password = req.getPassword();
        UmsAdmin umsAdmin = umsAdminService.getUmsAdminByUsername(username);

        Map<String, String> map = new HashMap<>();
        if (umsAdmin == null) {
            map.put("msg", "用户名不存在");
            return map;
        }
        if (!umsAdmin.getPassword().equals(password)) {
            map.put("msg", "密码错误");
            return map;
        }
        String token = jwtUtil.generateToken(username);
        response.setHeader(JwtUtil.HEADER, token);
        response.setHeader("Access-control-Expost-Headers", JwtUtil.HEADER);
        map.put("token", token);
        return map;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // 退出登录
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            //清除认证
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "退出成功";
    }

    @GetMapping("/list")
    public List<UmsAdmin> list() {
        return umsAdminService.listUmsAdmin();
    }

    @GetMapping("/list/resource")
    public List<UmsResource> getUserResouce(@RequestParam("username") String username) {
        return umsAdminService.getResourceByUsername(username);
    }

}
