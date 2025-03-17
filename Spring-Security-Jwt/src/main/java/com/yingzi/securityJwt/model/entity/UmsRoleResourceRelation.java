package com.yingzi.securityJwt.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;


/**
 * @author yingzi
 * @date 2025/3/17:14:34
 */
public class UmsRoleResourceRelation {

    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 资源ID
     */
    private Long resourceId;
}
