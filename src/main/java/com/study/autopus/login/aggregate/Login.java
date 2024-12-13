package com.study.autopus.login.aggregate;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "member_info")
public class Login {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_mobile")
    private String memberMobile;

    @Column(name = "member_birth")
    private Date memberBirth;

    @Column(name = "member_address")
    private String memberAddress;

    @Column(name = "member_gender")
    private String memberGender;

    @Column(name = "member_email", unique = true) // 이메일 필드 추가
    private String memberEmail;

    @Column(name = "member_join")
    private Date memberJoin;

    @Column(name = "member_withdrawal")
    private Date memberWithdrawal;

    @Column(name = "member_status")
    private int memberStatus;
}
