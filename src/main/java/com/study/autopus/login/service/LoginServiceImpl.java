package com.study.autopus.login.service;

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
}
