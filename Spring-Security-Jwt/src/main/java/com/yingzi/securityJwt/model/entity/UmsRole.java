package com.yingzi.securityJwt.model.entity;


import lombok.Data;

import java.util.Date;

/**
 * @author yingzi
 * @date 2025/3/16:18:37
 */
@Data
public class UmsRole {

    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 后台用户数量
     */
    private Integer adminCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 启用状态：0->禁用；1->启用
     */
    private Integer status;

    private Integer sort;
}
