package com.yingzi.crudMybatis.service.impl;

import com.yingzi.crudMybatis.model.entity.PmsBrand;
import com.yingzi.crudMybatis.dao.PmsBrandMapper;
import com.yingzi.crudMybatis.model.req.BrandCreateReq;
import com.yingzi.crudMybatis.model.req.BrandPageReq;
import com.yingzi.crudMybatis.model.req.BrandUpdateReq;
import com.yingzi.crudMybatis.service.PmsBrandService;
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
        return brandMapper.pageList(page);
    }

    @Override
    public Long create(BrandCreateReq req) {
        PmsBrand brand = new PmsBrand();
        BeanUtils.copyProperties(req, brand);
        Long id = brandMapper.insert(brand);
        if (Objects.isNull(id)) {
            throw new RuntimeException("create brand failed");
        }
        return id;
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
        }
    }

    @Override
    public Boolean delete(Long id) {
        int count = brandMapper.deleteById(id);
        if (count == 1) {
            return true;
        } else {
            throw new RuntimeException("delete brand failed, id:" + id);
        }
    }
}
