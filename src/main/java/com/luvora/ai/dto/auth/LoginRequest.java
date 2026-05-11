package com.luvora.ai.dto.auth;

import jakarta.validation.constraints.NotBlank;


public record LoginRequest(
        @NotBlank(message = "Username or Email is required")
        String username,

        @NotBlank(message = "Password is required")
        String password
) {
}
