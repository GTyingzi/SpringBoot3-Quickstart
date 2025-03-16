package com.yingzi.security.model.entity;


import lombok.Data;

/**
 * @author yingzi
 * @date 2025/3/16:18:38
 */
@Data
public class UmsRoleMenuRelation {

    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;
}
