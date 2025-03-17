package com.yingzi.securityJwt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yingzi.securityJwt.model.entity.UmsResource;
import com.yingzi.securityJwt.model.entity.UmsRoleResourceRelation;

import java.util.List;

/**
 * @author yingzi
 * @date 2025/3/17:14:35
 */
public interface UmsRoleResourceRelationMapper extends BaseMapper<UmsRoleResourceRelation> {
    List<UmsResource> listResourceInRoleId(List<Long> roleIdList);
}
