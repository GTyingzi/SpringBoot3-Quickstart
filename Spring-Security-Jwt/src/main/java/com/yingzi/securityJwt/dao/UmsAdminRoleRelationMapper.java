package com.yingzi.securityJwt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yingzi.securityJwt.model.entity.UmsAdminRoleRelation;
import com.yingzi.securityJwt.model.entity.UmsRole;

import java.util.List;

/**
 * <p>
 * 后台用户和角色关系表 Mapper 接口
 * </p>
 *
 * @author yingzi
 * @since 2024-03-31
 */
public interface UmsAdminRoleRelationMapper extends BaseMapper<UmsAdminRoleRelation> {

    List<UmsRole> findRoleByAdminId(Long adminId);
}
