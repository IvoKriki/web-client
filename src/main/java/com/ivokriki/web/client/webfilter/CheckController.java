package com.ivokriki.web.client.webfilter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class CheckController {

    @GetMapping("/check")
    public Mono<String> checkEndpoint() {
        return Mono.just("Request successful!");
    }
}

