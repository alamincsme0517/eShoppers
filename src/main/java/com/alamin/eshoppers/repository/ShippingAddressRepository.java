package com.alamin.eshoppers.repository;

import com.alamin.eshoppers.domain.ShippingAddress;

public interface ShippingAddressRepository {
    ShippingAddress save(ShippingAddress convertTo);
}
