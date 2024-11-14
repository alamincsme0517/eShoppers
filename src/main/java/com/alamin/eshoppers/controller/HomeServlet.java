package com.alamin.eshoppers.controller;

import com.alamin.eshoppers.domain.Cart;
import com.alamin.eshoppers.dto.ProductDto;
import com.alamin.eshoppers.repository.CartItemRepositoryImpl;
import com.alamin.eshoppers.repository.CartRepositoryImpl;
import com.alamin.eshoppers.repository.ProductRepositoryImpl;
import com.alamin.eshoppers.service.CartService;
import com.alamin.eshoppers.service.CartServiceImpl;
import com.alamin.eshoppers.service.ProductService;
import com.alamin.eshoppers.service.ProductServiceImpl;
import com.alamin.eshoppers.utils.SecurityContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final Logger LOGGER =  LoggerFactory.getLogger(HomeServlet.class);
    private ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private CartService cartService = new CartServiceImpl(new CartRepositoryImpl(), new ProductRepositoryImpl(), new CartItemRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        LOGGER.info("Serving home page");

        final String attribute = req.getParameter("orderSuccess");

        if (attribute != null && Boolean.parseBoolean(attribute)) {
            req.setAttribute("message",
                    "<strong>Congratulations!</strong> Your order has been placed successfully");
        }


       List<ProductDto> allProducts = productService.findAllProductSortedByName();
       LOGGER.info("Total product found {} " , allProducts.size());


        if (SecurityContext.isAuthenticated(req)) {
            var currentUser = SecurityContext.getCurrentUser(req);
            System.out.println(currentUser);
            Cart cart = cartService.getCartByUser(currentUser);
            req.setAttribute("cart" , cart);
        }


       req.setAttribute("products", allProducts);
       req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
