package com.alamin.eshoppers.repository;

import com.alamin.eshoppers.domain.Order;
import com.alamin.eshoppers.domain.User;

import java.util.Set;

public interface OrderRepository {
    Order save(Order order);

    Set<Order> findOrderByUser(User currentUser);
}
