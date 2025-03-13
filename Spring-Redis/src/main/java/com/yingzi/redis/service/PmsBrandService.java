package com.yingzi.redis.service;

import com.yingzi.redis.model.entity.PmsBrand;
import com.yingzi.redis.model.req.BrandCreateReq;
import com.yingzi.redis.model.req.BrandUpdateReq;

/**
 * @author yingzi
 * @date 2025/3/10:20:58
 */
public interface PmsBrandService {

    // 增
    Long create(BrandCreateReq req);

    // 查
    PmsBrand findById(Long id);

    // 更新
    Boolean update(BrandUpdateReq req);

    // 删
    Boolean delete(Long id);
}
