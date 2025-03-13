package com.yingzi.crudMybatis.service;

import com.yingzi.crudMybatis.model.entity.PmsBrand;
import com.yingzi.crudMybatis.model.req.BrandCreateReq;
import com.yingzi.crudMybatis.model.req.BrandPageReq;
import com.yingzi.crudMybatis.model.req.BrandUpdateReq;

import java.util.List;

/**
 * @author yingzi
 * @date 2025/3/10:20:58
 */
public interface PmsBrandService {

    // 默认分页查询
    List<PmsBrand> pageList(BrandPageReq page);

    // 增
    Long create(BrandCreateReq req);

    // 查
    PmsBrand findById(Long id);

    // 更新
    Boolean update(BrandUpdateReq req);

    // 删
    Boolean delete(Long id);
}
