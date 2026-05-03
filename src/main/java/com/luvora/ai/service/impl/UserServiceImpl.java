package com.luvora.ai.service.impl;

import com.luvora.ai.dto.auth.UserProfileResponse;
import com.luvora.ai.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserProfileResponse getProfile(Long userId) {
        return null;
    }
}
