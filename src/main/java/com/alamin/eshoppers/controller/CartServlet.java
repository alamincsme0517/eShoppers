package com.alamin.eshoppers.controller;

import com.alamin.eshoppers.domain.Cart;
import com.alamin.eshoppers.domain.User;
import com.alamin.eshoppers.repository.CartItemRepositoryImpl;
import com.alamin.eshoppers.repository.CartRepositoryImpl;
import com.alamin.eshoppers.repository.ProductRepositoryImpl;
import com.alamin.eshoppers.service.Action;
import com.alamin.eshoppers.service.CartService;
import com.alamin.eshoppers.service.CartServiceImpl;
import com.alamin.eshoppers.utils.SecurityContext;
import com.alamin.eshoppers.utils.StringUtil;
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
    private final CartService cartService = new CartServiceImpl(new CartRepositoryImpl(), new ProductRepositoryImpl(), new CartItemRepositoryImpl());



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var productId = req.getParameter("productId");
        var cart = getCart(req);
        var action = req.getParameter("action");

        if (StringUtil.isNotEmpty(action)) {
            processCart(productId, action, cart);
            resp.sendRedirect("/checkout");
            return;
        }

        LOGGER.info("Received request to add product with id : {} to cart", productId );
        addProductToCart(productId, cart);
        resp.sendRedirect("/home");

    }

    private void processCart(String productId, String action, Cart cart) {
        switch (Action.valueOf(action.toUpperCase())){
            case ADD :
                LOGGER.info("Received request to add product with id : {} to cart", productId );
                cartService.addProductToCart(productId, cart);
                break;
            case REMOVE:
                LOGGER.info("Received request to remove product with id : {} to cart", productId );
                cartService.removeProductFromCart(productId, cart);
                break;

            case DELETE:
                LOGGER.info("Received request to delete product with id : {} to cart", productId );
                cartService.deleteProductFromCart(productId, cart);
                break;
        }
    }

    private void addProductToCart(String productId, Cart cart) {
        cartService.addProductToCart(productId, cart);
    }

    private Cart getCart(HttpServletRequest req) {
        final User currentUser = SecurityContext.getCurrentUser(req);
        return cartService.getCartByUser(currentUser);
    }
}
