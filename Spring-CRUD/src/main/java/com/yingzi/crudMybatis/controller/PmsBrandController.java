package com.yingzi.crudMybatis.controller;

import com.yingzi.crudMybatis.model.entity.PmsBrand;
import com.yingzi.crudMybatis.model.req.BrandCreateReq;
import com.yingzi.crudMybatis.model.req.BrandPageReq;
import com.yingzi.crudMybatis.model.req.BrandUpdateReq;
import com.yingzi.crudMybatis.service.PmsBrandService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/pageList")
    List<PmsBrand> pageList(@RequestBody BrandPageReq page){
        return brandService.pageList(page);
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
