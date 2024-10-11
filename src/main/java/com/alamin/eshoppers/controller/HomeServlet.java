package com.alamin.eshoppers.controller;

import com.alamin.eshoppers.dto.ProductDto;
import com.alamin.eshoppers.repository.ProductRepositoryImpl;
import com.alamin.eshoppers.service.ProductService;
import com.alamin.eshoppers.service.ProductServiceImpl;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      LOGGER.info("Serving home page");
       List<ProductDto> allProducts = productService.findAllProductSortedByName();
       req.setAttribute("products", allProducts);
       LOGGER.info("Total product found {} ", allProducts.size());
       req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req,resp);
    }
}
