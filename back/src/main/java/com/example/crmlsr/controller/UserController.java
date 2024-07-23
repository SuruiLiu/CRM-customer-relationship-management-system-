package com.example.crmlsr.controller;

import com.example.crmlsr.entity.Product;
import com.example.crmlsr.entity.User;
import com.example.crmlsr.service.UserService;
import com.example.crmlsr.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * function：
 * author：lsr
 * date：2024/3/13 19:34
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public Result add(@RequestBody User user){
        userService.insertUser(user);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userService.updateById(user);
        return Result.success();
    }
}
