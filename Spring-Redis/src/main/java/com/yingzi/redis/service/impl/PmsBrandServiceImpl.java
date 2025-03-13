package com.yingzi.redis.service.impl;

import com.yingzi.redis.config.RedisConfig;
import com.yingzi.redis.model.req.BrandUpdateReq;
import com.yingzi.redis.service.PmsBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.yingzi.redis.dao.PmsBrandMapper;
import com.yingzi.redis.model.entity.PmsBrand;
import com.yingzi.redis.model.req.BrandCreateReq;

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
    public Long create(BrandCreateReq req) {
        PmsBrand brand = new PmsBrand();
        BeanUtils.copyProperties(req, brand);
        brandMapper.insert(brand);
        if (Objects.isNull(brand.getId())) {
            throw new RuntimeException("create brand failed");
        }
        return brand.getId();
    }

    @Cacheable(value = RedisConfig.REDIS_KEY_DATABASE, key = "'pms:brand:'+ #id")
    @Override
    public PmsBrand findById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("id can not be null");
        }
        PmsBrand brand = brandMapper.selectById(id);
        if (Objects.isNull(brand)) {
            throw new RuntimeException("brand not found, id:" + id);
        }
        log.info("未命中缓存，查询数据库，id:{}", id);
        return brand;
    }

    @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE, key = "'pms:brand:'+ #req.id")
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

    @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE, key = "'pms:brand:'+ #id")
    @Override
    public Boolean delete(Long id) {
        int count = brandMapper.deleteById(id);
        if (count == 1) {
            return true;
        } else {
            throw new RuntimeException("delete brand failed, id:" + id);
        }    }
}
