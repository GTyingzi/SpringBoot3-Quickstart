package com.yingzi.redis.controller;


import com.yingzi.redis.model.entity.PmsBrand;
import com.yingzi.redis.model.req.BrandCreateReq;
import com.yingzi.redis.model.req.BrandUpdateReq;
import com.yingzi.redis.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author yingzi
 * @date 2025/3/10:21:04
 */
@RestController
@RequestMapping("/pmsBrand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService brandService;

    @PostMapping("/create")
    Long create(@RequestBody BrandCreateReq req) {
        return brandService.create(req);
    }

    @GetMapping("/get/{id}")
    PmsBrand findById(@PathVariable("id") Long id){
        return brandService.findById(id);
    }

    @PostMapping("/update")
    Boolean update(@RequestBody BrandUpdateReq req){
        return brandService.update(req);
    }

    @DeleteMapping("/delete/{id}")
    Boolean delete(@PathVariable Long id){
        return brandService.delete(id);
    }
}
