package com.example.crmlsr.service;

/**
 * function：
 * author：lsr
 * date：2024/3/13 19:33
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crmlsr.entity.User;
import com.example.crmlsr.exception.ServiceException;
import com.example.crmlsr.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService extends ServiceImpl<UserMapper,User> {

    @Resource
    UserMapper userMapper;

    public User selectByUsername(String username){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);//条件查询器直接查username
        return  getOne(queryWrapper);
    }

    public void insertUser(User user) {
        userMapper.insert(user);
    }

    public User login(User user) {
        User dbUser = selectByUsername(user.getUsername());
        if(dbUser == null){
            throw new ServiceException("账号不存在");
        }
        if(!user.getPassword().equals(dbUser.getPassword())){
            throw new ServiceException("用户名或密码错误");
        }
        return dbUser;
    }

    public User register(User user) {
        User dbUser = selectByUsername(user.getUsername());
        if(dbUser != null){
            throw new ServiceException("账号已存在");
        }
        user.setEname("默认用户名");
        userMapper.insert(user);
        return user;
    }
}
