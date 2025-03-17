package com.yingzi.securityJwt.service;

import com.yingzi.securityJwt.model.entity.UmsAdmin;
import com.yingzi.securityJwt.model.entity.UmsResource;

import java.util.List;

/**
 * @author yingzi
 * @date 2025/3/17:14:11
 */
public interface UmsAdminService {

    UmsAdmin getUmsAdminByUsername(String username);

    List<UmsAdmin> listUmsAdmin();

    List<UmsResource> getResourceByUsername(String username);
}
