package com.alamin.eshoppers.utils;

import com.alamin.eshoppers.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SecurityContext {
    public static final String AUTHENTICATION_KEY = "auth.Key";

    public static void login(HttpServletRequest req, User user) {
        HttpSession oldSession = req.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }

        HttpSession session = req.getSession(true);
        session.setAttribute(AUTHENTICATION_KEY, user);
    }

    public static void logout(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        session.removeAttribute(AUTHENTICATION_KEY);
    }

    public static User getCurrentUser(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        return (User) session.getAttribute(AUTHENTICATION_KEY);
    }

    public static boolean isAuthenticated (HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        return session.getAttribute(AUTHENTICATION_KEY) != null ;
    }
}
