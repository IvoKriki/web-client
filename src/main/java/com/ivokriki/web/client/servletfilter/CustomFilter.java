package com.ivokriki.web.client.servletfilter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;

@Component
public class CustomFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Log request parameters
        logRequestParameters(httpRequest);

        // Authentication and authorization
        if (!isAuthorized(httpRequest)) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }

        // Header formatting (adds a custom header)
        httpRequest.setAttribute("Formatted-Header", "Custom-Value");

        // Proceeds with request processing
        chain.doFilter(httpRequest, response);
    }

    private void logRequestParameters(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            logger.info("Request Param: {} = {}", paramName, paramValue);
        }
    }

    private boolean isAuthorized(HttpServletRequest request) {
        // Simple authentication example: checks a token in the request header
        String authToken = request.getHeader("Authorization");
        return "Bearer example-token".equals(authToken);
    }
}