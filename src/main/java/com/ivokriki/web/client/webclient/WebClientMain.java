package com.ivokriki.web.client.webclient;

import com.ivokriki.web.client.WebClientApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientMain {

    public static void main(String[] args) {
        SpringApplication.run(WebClientApplication.class, args);
        String url = "https://catfact.ninja/fact?max_length=140";

        WebClient.Builder clientBuilder = WebClient.builder();

        Fact fact = clientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(Fact.class)
                .block();

        System.out.println("-------------------------------------------------");
        System.out.println(fact);
        System.out.println("-------------------------------------------------");
    }

}
