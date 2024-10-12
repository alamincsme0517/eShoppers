package com.alamin.eshoppers.controller;
/*
 *  CREATED:  10/12/2024
 *  TIME   :  7:40 PM
 *  PROJECT:  eShoppers
 *  @AUTHOR:  Al Amin
 */

import com.alamin.eshoppers.domain.User;
import com.alamin.eshoppers.dto.LoginDto;
import com.alamin.eshoppers.exceptions.UserNotFoundException;
import com.alamin.eshoppers.repository.UserRepositoryImpl;
import com.alamin.eshoppers.service.UserService;
import com.alamin.eshoppers.service.UserServiceImpl;
import com.alamin.eshoppers.utils.SecurityContext;
import com.alamin.eshoppers.utils.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);

    private UserService userService = new UserServiceImpl(new UserRepositoryImpl());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Serving login page");

        String logout = req.getParameter("logout");
        if (logout != null && Boolean.parseBoolean(logout)){
            req.setAttribute("message", "You have successfully logged out!");
        }
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginDto loginDto = new LoginDto(req.getParameter("username"), req.getParameter("password"));

        LOGGER.info("Received login data: {} ", loginDto);

        var errors = ValidationUtil.getInstance().validate(loginDto);

        if (! errors.isEmpty()) {
            LOGGER.info("Failed to login, sending login from again");

            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }

        try {
            login(loginDto, req) ;

            LOGGER.info("login successful, redirecting to home page!");

            resp.sendRedirect("/home");
        } catch (UserNotFoundException e) {
            LOGGER.info("incorrect username/password", e);

            errors.put("username", "Incorrect username or password");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }

    }

    private void login(LoginDto loginDto, HttpServletRequest req) {
        User user = userService.varifyUser(loginDto);
        SecurityContext.login(req, user);
//        HttpSession oldSession = req.getSession(false);
//        if (oldSession != null) {
//            oldSession.invalidate();
//        }
//
//        HttpSession session = req.getSession(true);
//        session.setAttribute("user", user);
    }


}
