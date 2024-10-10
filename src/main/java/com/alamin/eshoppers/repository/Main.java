package com.alamin.eshoppers.repository;


import com.alamin.eshoppers.dto.ProductDto;
import com.alamin.eshoppers.service.ProductService;
import com.alamin.eshoppers.service.ProductServiceImpl;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create an instance of ProductRepositoryImpl
        ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

        // Get all products using ProductService
        List<ProductDto> products = productService.findAllProductSortedByName();

        // Print all products
        System.out.println("All Products:");
        for (ProductDto product : products) {
            System.out.println("ID: " + product.getId());
            System.out.println("Name: " + product.getName());
            System.out.println("Description: " + product.getDescription());
            System.out.println("Price: $" + product.getPrice());
            System.out.println("-----------------------------");
        }
    }
}
