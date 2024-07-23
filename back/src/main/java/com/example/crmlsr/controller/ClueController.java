package com.example.crmlsr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmlsr.entity.Clue;
import com.example.crmlsr.service.ClueService;
import com.example.crmlsr.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

/**
 * function：
 * author：lsr
 * date：2024/3/21 18:40
 */
@RestController
@RequestMapping("/clue")
public class ClueController {

    @Autowired
    ClueService clueService;

    @PostMapping("/add")
    public Result add(@RequestBody Clue clue){
        try {
            clueService.save(clue);
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
    public Result update(@RequestBody Clue clue){
        clueService.updateById(clue);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        clueService.removeById(id);
        return Result.success();
    }

    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String xname){
        QueryWrapper<Clue> queryWrapper = new QueryWrapper<Clue>().orderByAsc("xid");
        queryWrapper.like("xname",xname);
        Page<Clue> page = clueService.page(new Page<>(pageNum,pageSize),queryWrapper);
        return Result.success(page);
    }

}
