package com.ssm.tpssystem.dao;

import com.ssm.tpssystem.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
    public List<Product> findAllProduct();
}
