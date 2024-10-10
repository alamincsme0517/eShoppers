package com.alamin.eshoppers.service;

import com.alamin.eshoppers.repository.ProductRepository;
import com.alamin.eshoppers.dto.ProductDto;
import com.alamin.eshoppers.domain.Product;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository ;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> findAllProductSortedByName() {
        return productRepository.findAllProducts().stream()
                .map(this::convertToDto)
                .sorted(Comparator.comparing(ProductDto::getName))
                .collect(Collectors.toList());
    }

   private ProductDto convertToDto(Product product) {
        return new ProductDto(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice()
        );
   }
}
