package com.alamin.eshoppers.repository;

import com.alamin.eshoppers.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
