package com.ssm.tpssystem.dao;

import com.ssm.tpssystem.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    public List<User> findAllSales();
}
