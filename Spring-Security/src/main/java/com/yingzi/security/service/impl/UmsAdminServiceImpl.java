package com.yingzi.security.service.impl;

import com.yingzi.security.dao.UmsAdminMapper;
import com.yingzi.security.dao.UmsAdminRoleRelationMapper;
import com.yingzi.security.model.entity.UmsAdmin;
import com.yingzi.security.model.entity.UmsRole;
import com.yingzi.security.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yingzi
 * @date 2025/3/16:19:02
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Autowired
    private UmsAdminMapper umsAdminMapper;
    @Autowired
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    @Override
    public List<UmsAdmin> listUmsAdmin() {
        // 查询所有用户
        return umsAdminMapper.selectList(null);
    }

    @Override
    public UmsAdmin getUmsAdminById(Long id) {
        // 根据id查询用户
        return umsAdminMapper.selectById(id);
    }

    @Override
    public UmsAdmin getUmsAdminByUsername(String username) {
        // 根据用户名查询用户
        return umsAdminMapper.findByUsername(username);
    }

    @Override
    public List<UmsRole> listRoleByName(String username) {
        UmsAdmin umsAdmin = umsAdminMapper.findByUsername(username);
        if (umsAdmin == null) {
            throw new UsernameNotFoundException("用户未找到");
        }
        // 用户找到了，获取该用户的角色列表
        return umsAdminRoleRelationMapper.findRoleByAdminId(umsAdmin.getId());
    }
}
