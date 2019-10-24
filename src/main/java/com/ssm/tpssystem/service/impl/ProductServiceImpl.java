package com.ssm.tpssystem.service.impl;

import com.ssm.tpssystem.dao.ProductMapper;
import com.ssm.tpssystem.domain.Product;
import com.ssm.tpssystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findAllProduct() {
        List<Product> list = productMapper.findAllProduct();

        return list;
    }
}
