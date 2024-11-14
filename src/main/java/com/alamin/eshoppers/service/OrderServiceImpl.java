package com.alamin.eshoppers.service;

import com.alamin.eshoppers.domain.Order;
import com.alamin.eshoppers.domain.ShippingAddress;
import com.alamin.eshoppers.domain.User;
import com.alamin.eshoppers.dto.ShippingAddressDto;
import com.alamin.eshoppers.exceptions.CartItemNotFoundException;
import com.alamin.eshoppers.repository.CartRepository;
import com.alamin.eshoppers.repository.OrderRepository;
import com.alamin.eshoppers.repository.ShippingAddressRepository;

public class OrderServiceImpl implements OrderService{
    private final  OrderRepository orderRepository;
    private final ShippingAddressRepository shippingAddressRepository;
    private final  CartRepository cartRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ShippingAddressRepository shippingAddressRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.shippingAddressRepository = shippingAddressRepository;
        this.cartRepository = cartRepository;
    }


    @Override
    public void processOrder(ShippingAddressDto shippingAddressDto, User currentUser) {
        var shippingAddress = convertTo(shippingAddressDto);
        var saveShippingAddress = shippingAddressRepository.save(shippingAddress);

        var cart = cartRepository.findByUser(currentUser)
                .orElseThrow(
                        () -> new CartItemNotFoundException("cart not found by current users")
                );

        Order order = new Order();
        order.setCart(cart);
        order.setShippingAddress(saveShippingAddress);
        order.setShipped(false);
        order.setUser(currentUser);
        orderRepository.save(order);
    }

    private ShippingAddress convertTo(ShippingAddressDto shippingAddressDto) {
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setAddress(shippingAddressDto.getAddress());
        shippingAddress.setAddress2(shippingAddressDto.getAddress2());
        shippingAddress.setCountry(shippingAddressDto.getCountry());
        shippingAddress.setState(shippingAddressDto.getState());
        shippingAddress.setZip(shippingAddressDto.getZip());
        shippingAddress.setCountry(shippingAddressDto.getCountry());
        shippingAddress.setMobileNumber(shippingAddressDto.getMobileNumber());

        return shippingAddress;
    }
}
