package com.study.autopus.join.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class JoinDTO {
    private int memberId;
    private String memberName;
    private String memberMobile;
    private String memberBirth;
    private String memberAddress;
    private String memberGender;
    private Date memberJoin;
    private Date memberWithdrawal;
    private int memberStatus;
}
