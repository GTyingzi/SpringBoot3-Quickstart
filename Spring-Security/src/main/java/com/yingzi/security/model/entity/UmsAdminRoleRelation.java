package com.yingzi.security.model.entity;


import lombok.Data;

/**
 * @author yingzi
 * @date 2025/3/16:18:38
 */
@Data
public class UmsAdminRoleRelation {

    private Long id;

    private Long adminId;

    private Long roleId;
}
