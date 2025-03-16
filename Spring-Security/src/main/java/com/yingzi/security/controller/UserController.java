package com.yingzi.security.controller;

import com.yingzi.security.model.entity.UmsAdmin;
import com.yingzi.security.model.entity.UmsRole;
import com.yingzi.security.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yingzi
 * @date 2025/3/16:18:58
 */
@RestController
@RequestMapping("/user-info")
public class UserController {

    @Autowired
    private UmsAdminService umsAdminService;

    @GetMapping("/list/user")
    public List<UmsAdmin> allUser() {
        return umsAdminService.listUmsAdmin();
    }

    @GetMapping("/list/rolebyName")
    public List<UmsRole> listRoleByName(String username) {
        return umsAdminService.listRoleByName(username);
    }

    @GetMapping("role-rule")
    public String roleRule() {
        return "数据库设置：超级管理员可访问";
    }
}
