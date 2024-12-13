package com.study.autopus.login.service;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface LoginService {
    String loginByNaver(String code);

    void saveNaverUser(OAuth2AuthenticationToken authentication);
}
