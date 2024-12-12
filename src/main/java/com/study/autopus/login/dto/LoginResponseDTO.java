package com.study.autopus.login.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LoginResponseDTO {
    private int memberId;          // 회원 ID
    private String memberName;     // 회원 이름
    private String memberMobile;   // 회원 핸드폰 번호
    private String memberAddress;  // 회원 주소
    private String memberGender;   // 회원 성별
}