package com.study.autopus.login.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LoginRequestDTO {
    private String memberName;
    private String memberMobile;
}
