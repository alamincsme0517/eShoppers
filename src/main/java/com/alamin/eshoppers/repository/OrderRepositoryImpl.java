package com.alamin.eshoppers.repository;

import com.alamin.eshoppers.domain.Order;
import com.alamin.eshoppers.domain.User;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

public class OrderRepositoryImpl implements OrderRepository{
    private static final Set<Order> ORDERS = new CopyOnWriteArraySet<>() ;

    @Override
    public Order save(Order order) {
        ORDERS.add(order);
        return order;
    }

    @Override
    public Set<Order> findOrderByUser(User currentUser) {
        return ORDERS.stream().filter(order -> order.getUser().equals(currentUser)).collect(Collectors.toSet());
    }
}
