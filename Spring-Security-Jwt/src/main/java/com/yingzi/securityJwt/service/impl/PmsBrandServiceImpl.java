package com.yingzi.securityJwt.service.impl;

import com.yingzi.securityJwt.dao.PmsBrandMapper;
import com.yingzi.securityJwt.model.entity.PmsBrand;
import com.yingzi.securityJwt.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yingzi
 * @date 2025/3/17:15:48
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private PmsBrandMapper pmsBrandMapper;


    @Override
    public List<PmsBrand> list() {
        return pmsBrandMapper.selectList(null);
    }
}
