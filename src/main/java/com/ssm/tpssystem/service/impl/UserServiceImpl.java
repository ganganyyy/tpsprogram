package com.ssm.tpssystem.service.impl;

import com.ssm.tpssystem.dao.UserMapper;
import com.ssm.tpssystem.domain.User;
import com.ssm.tpssystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {

       // return userMapper.selectByUsername(username);
        //TODO:can't get connection
        User user=new User();
        user.setId(1);
        user.setUsername("gangan1");
        user.setPassword("123");
        user.setDuty("t");
        return user;
    }
}
