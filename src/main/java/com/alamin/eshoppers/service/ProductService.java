package com.alamin.eshoppers.service;

import com.alamin.eshoppers.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAllProductSortedByName();
}
