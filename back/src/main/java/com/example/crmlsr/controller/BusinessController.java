package com.example.crmlsr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmlsr.entity.Business;
import com.example.crmlsr.service.BusinessService;
import com.example.crmlsr.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;


/**
 * function：
 * author：lsr
 * date：2024/3/21 18:37
 */
@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    BusinessService businessService;


    @PostMapping("/add")
    public Result add(@RequestBody Business business){
        try {
            businessService.save(business);
        } catch (Exception e) {
            if(e instanceof DuplicateKeyException) {
                return Result.error("插入数据库错误");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Business business){
        businessService.updateById(business);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        businessService.removeById(id);
        return Result.success();
    }

    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String bname){
        QueryWrapper<Business> queryWrapper = new QueryWrapper<Business>().orderByAsc("bid");
        queryWrapper.like("bname",bname);
        Page<Business> page = businessService.page(new Page<>(pageNum,pageSize),queryWrapper);
        return Result.success(page);
    }

}
