package com.alamin.eshoppers.service;

import com.alamin.eshoppers.domain.User;
import com.alamin.eshoppers.dto.ShippingAddressDto;

public interface OrderService {
    void processOrder(ShippingAddressDto shippingAddress, User currentUser) ;
}
