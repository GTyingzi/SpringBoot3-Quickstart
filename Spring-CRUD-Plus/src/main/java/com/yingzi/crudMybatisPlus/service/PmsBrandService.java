package com.yingzi.crudMybatisPlus.service;


import com.yingzi.crudMybatisPlus.model.entity.PmsBrand;
import com.yingzi.crudMybatisPlus.model.req.BrandCreateReq;
import com.yingzi.crudMybatisPlus.model.req.BrandPageReq;
import com.yingzi.crudMybatisPlus.model.req.BrandUpdateReq;

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
