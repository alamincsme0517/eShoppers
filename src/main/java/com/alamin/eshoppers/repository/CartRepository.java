package com.alamin.eshoppers.repository;

import com.alamin.eshoppers.domain.Cart;
import com.alamin.eshoppers.domain.User;

import java.util.Optional;

public interface CartRepository {
    Optional<Cart> findByUser(User currentUser);

    Cart save(Cart cart);
    Cart update(Cart cart);

}
