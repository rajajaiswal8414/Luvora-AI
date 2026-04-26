package com.luvora.ai.service;

import com.luvora.ai.dto.auth.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
}
