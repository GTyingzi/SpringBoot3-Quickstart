package com.yingzi.securityJwt.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.yingzi.securityJwt.dao.UmsAdminMapper;
import com.yingzi.securityJwt.dao.UmsAdminRoleRelationMapper;
import com.yingzi.securityJwt.dao.UmsRoleResourceRelationMapper;
import com.yingzi.securityJwt.model.entity.UmsAdmin;
import com.yingzi.securityJwt.model.entity.UmsResource;
import com.yingzi.securityJwt.model.entity.UmsRole;
import com.yingzi.securityJwt.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yingzi
 * @date 2025/3/17:14:12
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    @Autowired
    private UmsRoleResourceRelationMapper umsRoleResourceRelationMapper;

    @Override
    public UmsAdmin getUmsAdminByUsername(String username) {
        // 根据用户名查询用户
        return umsAdminMapper.findByUsername(username);
    }

    @Override
    public List<UmsAdmin> listUmsAdmin() {
        // 查询所有用户
        return umsAdminMapper.selectList(null);
    }

    @Override
    public List<UmsResource> getResourceByUsername(String username) {
        UmsAdmin umsAdmin = umsAdminMapper.findByUsername(username);
        if (umsAdmin == null) {
            throw new UsernameNotFoundException("用户未找到");
        }
        // 用户找到了，获取该用户的角色列表
        List<UmsRole> umsRoleList = umsAdminRoleRelationMapper.findRoleByAdminId(umsAdmin.getId());
        if (CollectionUtils.isNotEmpty(umsRoleList)) {
            List<Long> roleIdList = umsRoleList.stream().map(UmsRole::getId).toList();
            return umsRoleResourceRelationMapper.listResourceInRoleId(roleIdList);
        }
        return null;
    }
}
