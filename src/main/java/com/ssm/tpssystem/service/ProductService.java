package com.ssm.tpssystem.service;

import com.ssm.tpssystem.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public List<Product> findAllProduct();
}
