package com.study.autopus.login.repository;

import com.study.autopus.login.aggregate.Login;
import com.study.autopus.login.aggregate.NaverToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NaverTokenRepository extends JpaRepository<NaverToken, Integer> {
    Optional<NaverToken> findByMember(Login member);
}
