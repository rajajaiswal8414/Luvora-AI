package com.luvora.ai.service.impl;

import com.luvora.ai.dto.auth.AuthResponse;
import com.luvora.ai.dto.auth.LoginRequest;
import com.luvora.ai.dto.auth.SignupRequest;
import com.luvora.ai.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthResponse signup(SignupRequest signupRequest) {
        return null;
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }
}
