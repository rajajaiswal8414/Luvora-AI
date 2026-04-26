package com.luvora.ai.dto.auth;

public record SignupRequest(
        String email,
        String name,
        String password
) {
}
