package com.ivokriki.web.client.servletfilter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ServletController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, authenticated user!";
    }
}
