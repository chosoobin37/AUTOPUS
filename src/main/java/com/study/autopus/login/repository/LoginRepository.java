package com.study.autopus.login.repository;

import com.study.autopus.login.aggregate.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    Optional<Login> findByMemberMobile(String mobile);
}
