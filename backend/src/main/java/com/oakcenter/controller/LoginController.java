package com.oakcenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oakcenter.common.Result;
import com.oakcenter.entity.User;
import com.oakcenter.mapper.UserMapper;
import com.oakcenter.utils.TokenUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class LoginController {
    @Resource
    UserMapper userMapper;
    @PostMapping("/login")
    Result<?> login(@RequestBody User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",user.getId());
        queryWrapper.eq("pwd",user.getPwd());
        User res = userMapper.selectOne(queryWrapper);
        if (res == null) {
            return Result.error("-1", "用户名或密码错误");
        }
        String token = TokenUtils.genToken(res);
        res.setToken(token);
        return Result.success(res);
    }
    @RequestMapping("/register")
    Result<?> register(@RequestBody User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", user.getId());
        if ( userMapper.selectOne(queryWrapper)!= null) {
            return Result.error("-1", "该ID已被注册");
        }
        user.setRole("common");
        user.setCreatedDate(new Date());
        if(userMapper.insert(user)>0)
            return Result.success(user);
        return Result.error("-1","注册失败");
    }
}
