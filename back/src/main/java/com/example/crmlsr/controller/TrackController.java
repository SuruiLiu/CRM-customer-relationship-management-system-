package com.example.crmlsr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmlsr.entity.Clue;
import com.example.crmlsr.entity.Track;
import com.example.crmlsr.service.TrackService;
import com.example.crmlsr.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * function：
 * author：lsr
 * date：2024/3/26 17:05
 */
@RestController
@RequestMapping("/track")
public class TrackController {

    @Autowired
    TrackService trackService;

    @PostMapping("/add")
    public Result add(@RequestBody Track track){
        try {
            trackService.save(track);
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
    public Result update(@RequestBody Track track){
        trackService.updateById(track);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        trackService.removeById(id);
        return Result.success();
    }

    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String name){
        QueryWrapper<Track> queryWrapper = new QueryWrapper<Track>().orderByAsc("tid");
        queryWrapper.like("name",name);
        Page<Track> page = trackService.page(new Page<>(pageNum,pageSize),queryWrapper);
        return Result.success(page);
    }

    @GetMapping("/selectByclue")
    public Result selectByxorigin(@RequestParam String name){
        QueryWrapper<Track> queryWrapper = new QueryWrapper<Track>().orderByAsc("tid");
        queryWrapper.like("name",name);
        List<Track> trackList = trackService.list(queryWrapper);
        return Result.success(trackList);
    }

}
