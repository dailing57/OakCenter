package com.oakcenter.controller;

import com.auth0.jwt.JWT;
import com.oakcenter.entity.User;
import com.oakcenter.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BaseController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HttpServletRequest request;

    public User getUser() {
        String token = request.getHeader("token");
        String aud = JWT.decode(token).getAudience().get(0);
        return userMapper.selectById(aud);
    }

    public String getAccountId(){
        String token = request.getHeader("token");
        String aud = JWT.decode(token).getAudience().get(0);
        return aud;
    }
}
