package com.luvora.ai.dto.auth;

public record AuthResponse(
        String token,
        UserProfileResponse userProfileResponse) {
}
