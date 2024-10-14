package com.alamin.eshoppers.repository;

import com.alamin.eshoppers.domain.CartItem;

public interface CartItemRepository {

    CartItem save(CartItem cartItem);

    CartItem update(CartItem cartItem);
}
