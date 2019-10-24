package com.ssm.tpssystem.service.impl;

import com.ssm.tpssystem.dao.UserMapper;
import com.ssm.tpssystem.domain.User;
import com.ssm.tpssystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public User findUser(String username, String password) {
        return userMapper.selectOneUser(username, password);
    }
    @Override
    public List<User> findAllSales(){
        List<User> list = userMapper.findAllSales();
     return list;
    }
}
