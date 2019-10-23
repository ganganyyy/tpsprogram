package com.ssm.tpssystem.dao;

import com.ssm.tpssystem.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    public List<User> findAllSales();

    @Select("SELECT USERNAME FROM USER WHERE ID = #{id}")
    String queryNameById(@Param("id") Integer id);
}
