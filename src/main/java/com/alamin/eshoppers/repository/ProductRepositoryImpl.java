package com.alamin.eshoppers.repository;

import com.alamin.eshoppers.domain.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository{
    public static final List<Product> ALL_PRODUCTS = List.of(
            new Product(
                    1L,
                    "iPad Pro",
                    "12.9-inch Silver 2TB Wi-Fi + Cellular",
                    BigDecimal.valueOf(2399)),
            new Product(
                    2L,
                    "M2 MacBook Air",
                    "Apple M2 chip with 8‑core CPU, 10‑core GPU, 16‑core Neural Engine 24GB unified memory",
                    BigDecimal.valueOf(2499)
            ),
            new Product(
                    3L,
                    "AirPods Max",
                    "AirPods Max — a perfect balance of exhilarating high-fidelity audio and the effortless magic of AirPods.",
                    BigDecimal.valueOf(549)
            ),
            new Product(
                    4L,
                    "Pixel 6a",
                    "Smart, powerful, helpful. And less than you think.",
                    BigDecimal.valueOf(449)
            ),
            new Product(
                    5L,
                    "Pixel Buds Pro",
                    "How premium sounds.",
                    BigDecimal.valueOf(199.99)
            )
    );

    @Override
    public List<Product> findAllProducts() {
        return ALL_PRODUCTS;
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return findAllProducts().stream().filter(product -> product.getId().equals(productId)).findFirst();
    }
}
