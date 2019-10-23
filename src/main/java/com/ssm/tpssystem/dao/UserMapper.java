package com.ssm.tpssystem.dao;

import com.ssm.tpssystem.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Mapper
@Repository
public interface UserMapper {
    User selectByUsername(@Param("username") String username);

    User selectOneUser(@Param("username")String username,@Param("password") String password);

    public List<User> findAllSales();


}
