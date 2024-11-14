package com.alamin.eshoppers.controller;

import com.alamin.eshoppers.dto.ShippingAddressDto;
import com.alamin.eshoppers.repository.CartItemRepositoryImpl;
import com.alamin.eshoppers.repository.CartRepositoryImpl;
import com.alamin.eshoppers.repository.ProductRepositoryImpl;
import com.alamin.eshoppers.service.CartService;
import com.alamin.eshoppers.service.CartServiceImpl;
import com.alamin.eshoppers.utils.SecurityContext;
import com.alamin.eshoppers.utils.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private CartService cartService = new CartServiceImpl(new CartRepositoryImpl(), new ProductRepositoryImpl(), new CartItemRepositoryImpl());

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addCartToUi(req);
        req.setAttribute("countries", getCountries());

        LOGGER.info("serving order page");
        req.getRequestDispatcher("/WEB-INF/order.jsp").forward(req, resp);
    }

    private void addCartToUi(HttpServletRequest req) {
        if (SecurityContext.isAuthenticated(req)) {
            var currentUser = SecurityContext.getCurrentUser(req);
            var cart = cartService.getCartByUser(currentUser);
            req.setAttribute("cart", cart);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var shippingAddress = copyParametersTo(req);
        var errors = ValidationUtil.getInstance().validate(shippingAddress);

        if (!errors.isEmpty()) {
            System.out.println(getCountries());
            req.setAttribute("countries", getCountries());
            req.setAttribute("errors", errors);
            req.setAttribute("shippingAddress", shippingAddress);
            addCartToUi(req);
            req.getRequestDispatcher("/WEB-INF/order.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/home?orderSuccess=true");
        }

    }

    private ShippingAddressDto copyParametersTo(HttpServletRequest req) {
        var shippingAddress = new ShippingAddressDto();
        shippingAddress.setAddress(req.getParameter("address"));
        shippingAddress.setAddress2(req.getParameter("address2"));
        shippingAddress.setState(req.getParameter("state"));
        shippingAddress.setZip(req.getParameter("zip"));
        shippingAddress.setCountry(req.getParameter("country"));
        shippingAddress.setMobileNumber(req.getParameter("mobileNumber"));

        return shippingAddress ;
    }

    private List<String> getCountries() {
        return List.of("Bangladesh", "Japan", "South Korea", "China", "India", "Saudi Arabia", "United State Of Emirates");
    }
}
