package com.example.crmlsr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmlsr.entity.Linkman;
import com.example.crmlsr.service.LinkmanService;
import com.example.crmlsr.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;


/**
 * function：
 * author：lsr
 * date：2024/3/19 14:17
 */
@RestController
@RequestMapping("/linkman")
public class LinkmanController {

    @Autowired
    LinkmanService linkmanService;

    @PostMapping("/add")
    public Result add(@RequestBody Linkman linkman){
        try {
            linkmanService.save(linkman);
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
    public Result update(@RequestBody Linkman linkman){
        linkmanService.updateById(linkman);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        linkmanService.removeById(id);
        return Result.success();
    }

    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String lname){
        QueryWrapper<Linkman> queryWrapper = new QueryWrapper<Linkman>().orderByAsc("lid");
        queryWrapper.like("lname",lname);
        Page<Linkman> page = linkmanService.page(new Page<>(pageNum,pageSize),queryWrapper);
        return Result.success(page);
    }


}
