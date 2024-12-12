package com.study.autopus.login.service;

import com.study.autopus.login.dto.LoginResponseDTO;

public interface LoginService {
    LoginResponseDTO loginByNaver(String code, String naver);
}
