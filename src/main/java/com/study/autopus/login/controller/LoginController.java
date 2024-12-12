package com.study.autopus.login.controller;

import com.study.autopus.login.service.LoginService;
import com.study.autopus.login.dto.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService; // loginService 주입
    }

    @GetMapping("/naver") // 실제 경로: /login/naver
    public ResponseEntity<LoginResponseDTO> loginByNaver(@RequestParam(name = "code") String code) {
        // LoginResponseDTO를 반환하는 서비스 호출
        LoginResponseDTO responseDTO = loginService.loginByNaver(code, "NAVER");
        return ResponseEntity.ok(responseDTO); // HTTP 200 OK와 함께 반환
    }

}
