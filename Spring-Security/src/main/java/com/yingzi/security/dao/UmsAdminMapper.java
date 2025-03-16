package com.yingzi.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yingzi.security.model.entity.UmsAdmin;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author yingzi
 * @since 2024-03-31
 */
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    UmsAdmin findByUsername(String username);

}
