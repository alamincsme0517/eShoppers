package com.alamin.eshoppers.dao;

import com.alamin.eshoppers.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    public List<Product> findAllProducts() ;
    public Optional<Product> findById(Long productId);
}
