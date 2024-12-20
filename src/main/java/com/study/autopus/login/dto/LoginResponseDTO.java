package com.study.autopus.login.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LoginResponseDTO {
    private int memberId;
    private String memberName;
    private String memberMobile;
    private String memberAddress;
    private String memberGender;
}