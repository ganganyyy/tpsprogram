package com.ssm.tpssystem.dao;

import com.ssm.tpssystem.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
    public List<Product> findAllProduct();

    @Select("SELECT CUSIP FROM PRODUCT WHERE ID = #{id}")
    String queryCusipById(@Param("id") Integer id);
}
