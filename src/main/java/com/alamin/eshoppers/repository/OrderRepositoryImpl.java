package com.alamin.eshoppers.repository;

import com.alamin.eshoppers.domain.Order;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class OrderRepositoryImpl implements OrderRepository{
    private static final Set<Order> ORDERS = new CopyOnWriteArraySet<>() ;

    @Override
    public Order save(Order order) {
        ORDERS.add(order);
        return order;
    }
}
