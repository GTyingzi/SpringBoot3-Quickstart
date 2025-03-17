package com.yingzi.securityJwt.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.yingzi.securityJwt.dao.UmsAdminMapper;
import com.yingzi.securityJwt.dao.UmsAdminRoleRelationMapper;
import com.yingzi.securityJwt.dao.UmsRoleResourceRelationMapper;
import com.yingzi.securityJwt.model.entity.UmsAdmin;
import com.yingzi.securityJwt.model.entity.UmsResource;
import com.yingzi.securityJwt.model.entity.UmsRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.security.core.userdetails.User.withUsername;

/**
 * @author yingzi
 * @date 2025/3/17:13:34
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    @Autowired
    private UmsRoleResourceRelationMapper umsRoleResourceRelationMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UmsAdmin umsAdmin = umsAdminMapper.findByUsername(username);
        if (umsAdmin == null) {
            throw new UsernameNotFoundException("用户未找到");
        }
        // 用户找到了，获取该用户的角色列表
        List<UmsRole> umsRoleList = umsAdminRoleRelationMapper.findRoleByAdminId(umsAdmin.getId());

        User.UserBuilder builder = withUsername(umsAdmin.getUsername());
        builder.password(umsAdmin.getPassword());
        builder.roles(umsRoleList.stream().map(UmsRole::getName).toArray(String[]::new));
        return builder.build();
    }

    /**
     * 获取用户权限信息（角色、菜单权限）
     * @param username
     * @return
     */
    public List<GrantedAuthority> getUserAuthority(String username) {
        UmsAdmin umsAdmin = umsAdminMapper.findByUsername(username);
        if (umsAdmin == null) {
            throw new UsernameNotFoundException("用户未找到");
        }
        // 用户找到了，获取该用户的角色列表
        List<UmsRole> umsRoleList = umsAdminRoleRelationMapper.findRoleByAdminId(umsAdmin.getId());
        if (CollectionUtils.isNotEmpty(umsRoleList)) {
            List<Long> roleIdList = umsRoleList.stream().map(UmsRole::getId).toList();
            List<UmsResource> umsResourceList = umsRoleResourceRelationMapper.listResourceInRoleId(roleIdList);
            if (CollectionUtils.isNotEmpty(umsResourceList)) {
                List<String> resourceList = umsResourceList.stream().map(UmsResource::getUrl).toList();
                return AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", resourceList));
            }
        }
        throw new UsernameNotFoundException("用户暂未任何权限");
    }
}