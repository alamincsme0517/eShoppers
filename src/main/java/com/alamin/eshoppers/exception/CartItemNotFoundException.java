package com.alamin.eshoppers.exception;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException() {

    }
    public CartItemNotFoundException(String message) {
        super(message);
    }
}
