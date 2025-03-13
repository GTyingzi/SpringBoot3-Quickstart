package com.yingzi.crudMybatis.dao;

import com.yingzi.crudMybatis.model.entity.PmsBrand;
import com.yingzi.crudMybatis.model.req.BrandPageReq;

import java.util.List;

/**
 * @author yingzi
 * @date 2025/3/10:20:54
 */
public interface PmsBrandMapper {

    List<PmsBrand> pageList(BrandPageReq page);

    Long insert(PmsBrand brand);

    PmsBrand selectById(Long id);

    int updateById(PmsBrand brand);

    int deleteById(Long id);


}
