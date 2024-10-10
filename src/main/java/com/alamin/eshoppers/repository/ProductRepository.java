package com.alamin.eshoppers.repository;

import com.alamin.eshoppers.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    public List<Product> findAllProducts() ;
    public Optional<Product> findById(Long productId);
}
