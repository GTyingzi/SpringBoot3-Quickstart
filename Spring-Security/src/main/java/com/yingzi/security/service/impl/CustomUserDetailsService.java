package com.yingzi.security.service.impl;

import com.yingzi.security.dao.UmsAdminMapper;
import com.yingzi.security.dao.UmsAdminRoleRelationMapper;
import com.yingzi.security.model.entity.UmsAdmin;
import com.yingzi.security.model.entity.UmsRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.security.core.userdetails.User.withUsername;

/**
 * @author yingzi
 * @date 2025/3/16:18:42
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UmsAdmin umsAdmin = umsAdminMapper.findByUsername(username);
        if (umsAdmin == null) {
            throw new UsernameNotFoundException("用户未找到");
        }
        // 用户找到了，获取该用户的角色列表
        List<UmsRole> umsRoleList = umsAdminRoleRelationMapper.findRoleByAdminId(umsAdmin.getId());

        UserBuilder builder = withUsername(umsAdmin.getUsername());
        builder.password(umsAdmin.getPassword());
        builder.roles(umsRoleList.stream().map(UmsRole::getName).toArray(String[]::new));
        return builder.build();
    }
}
