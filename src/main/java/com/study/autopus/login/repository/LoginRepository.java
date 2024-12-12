package com.study.autopus.login.repository;

import com.study.autopus.login.aggregate.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Integer> {

}
