package com.luvora.ai.dto.auth;

public record UserProfileResponse(
        Long id,
        String username,
        String name
        ) {
}
