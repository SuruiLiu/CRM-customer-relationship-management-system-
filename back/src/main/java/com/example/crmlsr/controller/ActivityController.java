package com.example.crmlsr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmlsr.entity.Activity;
import com.example.crmlsr.entity.Clue;
import com.example.crmlsr.service.ActivityService;
import com.example.crmlsr.service.ClueService;
import com.example.crmlsr.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * function：
 * author：lsr
 * date：2024/3/18 16:15
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Autowired
    ClueService clueService;

    @GetMapping("/selectAll")
    public Result selectAll(){
        List<Activity> activityList = activityService.list(new QueryWrapper<Activity>().orderByAsc("actid"));
        return Result.success(activityList);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Activity activity){
        try {
            activityService.save(activity);
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
    public Result update(@RequestBody Activity activity){
        activityService.updateById(activity);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        activityService.removeById(id);
        return Result.success();
    }

    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String actname){
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<Activity>().orderByAsc("actid");
        queryWrapper.like("actname",actname);
        Page<Activity> page = activityService.page(new Page<>(pageNum,pageSize),queryWrapper);
        return Result.success(page);
    }

    @GetMapping("/selectByxorigin")
    public Result selectByxorigin(@RequestParam String actname){
        QueryWrapper<Clue> queryWrapper = new QueryWrapper<Clue>().orderByAsc("xid");
        queryWrapper.like("xorigin",actname);
        List<Clue> clueList = clueService.list(queryWrapper);
        return Result.success(clueList);
    }

}
