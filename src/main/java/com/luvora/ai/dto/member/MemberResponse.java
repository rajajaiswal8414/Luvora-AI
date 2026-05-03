package com.luvora.ai.dto.member;

import com.luvora.ai.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId,
        String email,
        String name,
        String avatar,
        ProjectRole projectRole,
        Instant invitedAt
) {
}
