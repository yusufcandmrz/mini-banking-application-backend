package com.yusufcandmrz.minibank.service;

import com.yusufcandmrz.minibank.dto.request.LoginRequest;
import com.yusufcandmrz.minibank.dto.request.RegisterRequest;
import com.yusufcandmrz.minibank.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User register(RegisterRequest request);

    User login(LoginRequest request);
}
