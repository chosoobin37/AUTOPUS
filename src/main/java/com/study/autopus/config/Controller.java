package com.study.autopus.config;


import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/healthcheck")
    public String healthcheck() {
        return "healthcheck";
    }
}
