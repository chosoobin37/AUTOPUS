package com.study.autopus.login.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LoginRequestDTO {
    private String memberName;     // 회원 이름 (로그인 요청 시 사용)
    private String memberMobile;   // 회원 핸드폰 번호 (로그인 요청 시 사용)
}
