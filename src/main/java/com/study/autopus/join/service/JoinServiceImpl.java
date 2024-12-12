package com.study.autopus.join.service;

import com.study.autopus.join.repository.JoinRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinServiceImpl implements JoinService{

    private final ModelMapper  modelMapper;
    private final JoinRepository joinRepository;

    @Autowired
    public JoinServiceImpl(ModelMapper modelMapper, JoinRepository joinRepository) {
        this.modelMapper = modelMapper;
        this.joinRepository = joinRepository;
    }
}
