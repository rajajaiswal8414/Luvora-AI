package com.luvora.ai.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @NotBlank(message = "Username or Email is required")
        @Pattern(
                regexp = "^(?:[a-zA-Z0-9_]{3,20}|[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+)$",
                message = "Must be a valid username (3-20 chars, letters/numbers/_) or a valid email"
        )
        String username,

        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 50, message = "Name must be between 2 and 50 characters")
        String name,

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).+$",
                message = "Password must contain uppercase, lowercase, number, and special character"
        )
        String password
) {
}
