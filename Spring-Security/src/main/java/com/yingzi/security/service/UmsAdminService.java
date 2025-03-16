package com.yingzi.security.service;

import com.yingzi.security.model.entity.UmsAdmin;
import com.yingzi.security.model.entity.UmsRole;

import java.util.List;

/**
 * @author yingzi
 * @date 2025/3/16:19:02
 */
public interface UmsAdminService {
    List<UmsAdmin> listUmsAdmin();

    UmsAdmin getUmsAdminById(Long id);

    UmsAdmin getUmsAdminByUsername(String username);

    List<UmsRole> listRoleByName(String username);
}
