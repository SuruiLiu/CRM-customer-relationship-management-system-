package com.example.crmlsr.controller;

import com.example.crmlsr.entity.User;
import com.example.crmlsr.service.UserService;
import com.example.crmlsr.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * function：
 * author：lsr
 * date：2024/3/13 20:21
 */

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public Result hello(){return Result.success("success");}

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        user = userService.login(user);
        return Result.success(user);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        user = userService.register(user);
        return Result.success(user);
    }
}
