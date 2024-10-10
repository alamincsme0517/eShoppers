package com.alamin.eshoppers.services;

import com.alamin.eshoppers.dao.ProductRepository;
import com.alamin.eshoppers.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAllProducts();
}
