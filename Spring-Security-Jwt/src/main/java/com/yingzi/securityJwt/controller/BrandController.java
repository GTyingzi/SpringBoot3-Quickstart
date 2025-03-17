package com.yingzi.securityJwt.controller;

import com.yingzi.securityJwt.model.entity.PmsBrand;
import com.yingzi.securityJwt.model.entity.UmsAdmin;
import com.yingzi.securityJwt.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yingzi
 * @date 2025/3/17:15:41
 */

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private PmsBrandService pmsBrandService;


    @GetMapping("/list")
    public List<PmsBrand> list() {
        return pmsBrandService.list();
    }
}
