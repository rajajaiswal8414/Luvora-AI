package com.luvora.ai.mapper;

import com.luvora.ai.dto.auth.SignupRequest;
import com.luvora.ai.dto.auth.UserProfileResponse;
import com.luvora.ai.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserEntity(SignupRequest request);
    UserProfileResponse toUserProfileResponse(User user);
}
