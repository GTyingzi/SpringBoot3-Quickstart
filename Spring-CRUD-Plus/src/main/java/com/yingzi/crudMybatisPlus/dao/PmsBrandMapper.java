package com.yingzi.crudMybatisPlus.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yingzi.crudMybatisPlus.model.entity.PmsBrand;


/**
 * @author yingzi
 * @date 2025/3/10:20:54
 */
public interface PmsBrandMapper extends BaseMapper<PmsBrand> {

    // 定义分页
    Page<PmsBrand> pageList(Page<PmsBrand> page);

}
