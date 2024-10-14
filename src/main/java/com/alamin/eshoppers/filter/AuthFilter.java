package com.alamin.eshoppers.filter;

import com.alamin.eshoppers.utils.SecurityContext;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Stream;

@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {
    private static final String[] ALLOWED_CONTENTS = {".css", ".png", ".js", ".jpg", "home", "login", "signup"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = (HttpServletRequest) request;
        var requestedUri = req.getRequestURI() ;

        boolean allowed = Stream.of(ALLOWED_CONTENTS)
                .anyMatch(requestedUri::contains);
        if (requestedUri.equals("/") || allowed || SecurityContext.isAuthenticated(req))  {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/login");
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
