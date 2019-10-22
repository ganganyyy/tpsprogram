package com.ssm.tpssystem.service;


import com.ssm.tpssystem.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<User> findAllSales();
}
