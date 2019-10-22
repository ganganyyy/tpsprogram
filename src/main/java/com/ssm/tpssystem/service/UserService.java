package com.ssm.tpssystem.service;

import com.ssm.tpssystem.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByUserName(String username);
}
