package com.alamin.eshoppers.repository;

import com.alamin.eshoppers.domain.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user) ;

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
