package com.oakcenter.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oakcenter.common.Result;
import com.oakcenter.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/manage")
public class ManageController extends BaseController{
    @Resource
    UserMapper userMapper;
    @GetMapping("/all")
    public Result<?> getUserList(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "10") Integer pageSize){
        return Result.success(userMapper.selectPage(new Page<>(pageNum,pageSize),null));
    }
}
