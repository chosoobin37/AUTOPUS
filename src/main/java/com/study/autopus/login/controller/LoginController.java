package com.study.autopus.login.controller;

import com.study.autopus.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login/naver")
    public ResponseEntity<String> loginByNaver(@RequestParam(name = "code") String code) {
        // 네이버 인증 코드 처리 및 사용자 정보 조회
        String userInfo = loginService.loginByNaver(code);

        // 사용자 정보 반환 (예제)
        return ResponseEntity.ok("로그인 성공! 사용자 정보: " + userInfo);
    }

    @GetMapping("/home")
    public String homePage(OAuth2AuthenticationToken authentication) {
        // 네이버 사용자 정보 저장
        loginService.saveNaverUser(authentication);
        return "로그인 성공! 환영합니다.";
    }
}
