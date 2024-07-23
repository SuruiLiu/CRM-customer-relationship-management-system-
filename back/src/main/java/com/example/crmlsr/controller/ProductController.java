package com.example.crmlsr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmlsr.entity.Product;
import com.example.crmlsr.service.ProductService;
import com.example.crmlsr.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * function：
 * author：lsr
 * date：2024/3/16 14:22
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/selectAll")
    public Result selectAll(){
        List<Product> productList = productService.list(new QueryWrapper<Product>().orderByAsc("p_id"));
        return Result.success(productList);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Product product){
        try {
            productService.save(product);
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
    public Result update(@RequestBody Product product){
        productService.updateById(product);
        return Result.success();
    }

    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> pids){
        productService.removeBatchByIds(pids);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        productService.removeById(id);
        return Result.success();
    }

    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String pname){
        QueryWrapper<Product> queryWrapper = new QueryWrapper<Product>().orderByAsc("p_id");
        queryWrapper.like("p_name",pname);
        Page<Product> page = productService.page(new Page<>(pageNum,pageSize),queryWrapper);
        return Result.success(page);
    }
}
