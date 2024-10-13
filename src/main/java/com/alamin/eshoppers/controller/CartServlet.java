package com.alamin.eshoppers.controller;

import com.alamin.eshoppers.domain.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/add-to-cart")
public class CartServlet extends HttpServlet {
    private final Logger LOGGER = LoggerFactory.getLogger(CartServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var productId = req.getParameter("productId");
        LOGGER.info("Received request to add product with id : {} to cart", productId );

        var cart = getCart(req);
        addProductToCart(productId, cart);
    }

    private void addProductToCart(String productId, Object cart) {

    }

    private Object getCart(HttpServletRequest req) {
        return new Cart();
    }
}
