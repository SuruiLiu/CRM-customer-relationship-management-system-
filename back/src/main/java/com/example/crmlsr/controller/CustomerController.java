package com.example.crmlsr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmlsr.entity.Customer;
import com.example.crmlsr.service.CustomerService;
import com.example.crmlsr.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * function：
 * author：lsr
 * date：2024/3/19 14:17
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public Result add(@RequestBody Customer customer){
        try {
            customerService.save(customer);
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
    public Result update(@RequestBody Customer customer){
        customerService.updateById(customer);
        return Result.success();
    }

    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> cids){
        customerService.removeBatchByIds(cids);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        customerService.removeById(id);
        return Result.success();
    }

    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String cname){
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<Customer>().orderByAsc("cid");
        queryWrapper.like("cname",cname);
        Page<Customer> page = customerService.page(new Page<>(pageNum,pageSize),queryWrapper);
        return Result.success(page);
    }


}
