package com.ivokriki.web.client.webfilter;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CustomWebFilter implements WebFilter {
    private static final Logger logger = LoggerFactory.getLogger(CustomWebFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // 1. Logging
        logger.info("Request path: {} with parameters: {}",
                exchange.getRequest().getPath(),
                exchange.getRequest().getQueryParams());

        // 2. Authentication and authorization
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        logger.info("Authorization header received: {}", authHeader); // Additional logging

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.warn("Unauthorized request: Missing or invalid Authorization header");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Validating token
        String token = authHeader.substring(7);
        logger.info("Extracted token: {}", token);

        if (!"valid-token".equals(token)) {
            logger.warn("Forbidden request: Invalid token");
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

        // 3. Formatting JSON
        MediaType contentType = exchange.getRequest().getHeaders().getContentType();
        if (contentType != null && MediaType.APPLICATION_JSON.equals(contentType)) {
            logger.info("Request body is JSON, formatting...");
        } else {
            logger.info("Request body is not JSON or Content-Type is missing");
        }

        return chain.filter(exchange); // Move on to next filter
    }
}
