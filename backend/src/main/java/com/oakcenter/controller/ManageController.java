package com.oakcenter.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oakcenter.common.Result;
import com.oakcenter.entity.User;
import com.oakcenter.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

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
    @PostMapping("/add")
    public Result<?> addAdmin(@RequestBody User user){
        user.setRole("admin");
        user.setCreatedDate(new Date());
        if(userMapper.insert(user)>0)
            return Result.success();
        return Result.error("-1","新增失败");
    }

    @PostMapping("/delete")
    public Result<?> delete(@RequestParam String id){
        if(userMapper.deleteById(id)>0)
            return Result.success();
        return Result.error("-1","删除失败");
    }
}
