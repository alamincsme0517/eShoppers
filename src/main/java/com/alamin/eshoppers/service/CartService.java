package com.alamin.eshoppers.service;

import com.alamin.eshoppers.domain.Cart;
import com.alamin.eshoppers.domain.User;

public interface CartService {
    Cart getCartByUser(User currentUser);

    void addProductToCart(String productId, Cart cart);

    void removeProductFromCart(String productId, Cart cart);

}
