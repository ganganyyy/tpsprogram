package com.ssm.tpssystem.service;
import java.util.List;

import com.ssm.tpssystem.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByUserName(String username);
    User findUser(String username,String password);
    public List<User> findAllSales();
}



