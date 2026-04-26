package com.luvora.ai.service;

import com.luvora.ai.dto.auth.AuthResponse;
import com.luvora.ai.dto.auth.LoginRequest;
import com.luvora.ai.dto.auth.SignupRequest;

public interface AuthService {
    AuthResponse signup(SignupRequest signupRequest);

    AuthResponse login(LoginRequest loginRequest);
}
