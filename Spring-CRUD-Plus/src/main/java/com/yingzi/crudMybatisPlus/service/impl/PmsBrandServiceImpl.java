package com.yingzi.crudMybatisPlus.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yingzi.crudMybatisPlus.dao.PmsBrandMapper;
import com.yingzi.crudMybatisPlus.model.entity.PmsBrand;
import com.yingzi.crudMybatisPlus.model.req.BrandCreateReq;
import com.yingzi.crudMybatisPlus.model.req.BrandPageReq;
import com.yingzi.crudMybatisPlus.model.req.BrandUpdateReq;
import com.yingzi.crudMybatisPlus.service.PmsBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author yingzi
 * @date 2025/3/10:21:00
 */
@Slf4j
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public List<PmsBrand> pageList(BrandPageReq page) {
        Page<PmsBrand> pmsBrandPage = brandMapper.pageList(new Page<>(page.getPageNum(), page.getPageSize()));
        return pmsBrandPage.getRecords();
    }

    @Override
    public Long create(BrandCreateReq req) {
        PmsBrand brand = new PmsBrand();
        BeanUtils.copyProperties(req, brand);
        brandMapper.insert(brand);
        if (Objects.isNull(brand.getId())) {
            throw new RuntimeException("create brand failed");
        }
        return brand.getId();
    }

    @Override
    public PmsBrand findById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("id can not be null");
        }
        PmsBrand brand = brandMapper.selectById(id);
        if (Objects.isNull(brand)) {
            throw new RuntimeException("brand not found, id:" + id);
        }
        return brand;
    }

    @Override
    public Boolean update(BrandUpdateReq req) {
        PmsBrand brand = new PmsBrand();
        BeanUtils.copyProperties(req, brand);
        int count = brandMapper.updateById(brand);
        if (count == 1) {
            return true;
        } else {
            throw new RuntimeException("update brand failed, id:" + req.getId());
        }    }

    @Override
    public Boolean delete(Long id) {
        int count = brandMapper.deleteById(id);
        if (count == 1) {
            return true;
        } else {
            throw new RuntimeException("delete brand failed, id:" + id);
        }    }
}
