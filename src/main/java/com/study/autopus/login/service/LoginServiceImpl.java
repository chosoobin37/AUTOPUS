package com.study.autopus.login.service;

import com.study.autopus.login.dto.LoginResponseDTO;
import com.study.autopus.login.repository.LoginRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final ModelMapper  modelMapper;
    private final LoginRepository loginRepository;

    @Autowired
    public LoginServiceImpl(ModelMapper modelMapper, LoginRepository loginRepository) {
        this.modelMapper = modelMapper;
        this.loginRepository = loginRepository;
    }

    @Override
    public LoginResponseDTO loginByNaver(String code, String naver) {
//        if (!"NAVER".equalsIgnoreCase(provider)) {
//            throw new IllegalArgumentException("Unsupported provider: " + provider);
//        }

        // 예제: 네이버 API를 호출하고 사용자 정보를 반환하는 로직 추가
        // 실제 로직은 네이버 OAuth API 호출 후 데이터 매핑
        return LoginResponseDTO.builder()
                .memberId(1)
                .memberName("홍길동")
                .memberMobile("010-1234-5678")
                .memberAddress("서울특별시 강남구")
                .memberGender("M")
                .build();
    }
}
