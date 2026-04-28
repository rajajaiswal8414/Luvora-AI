package com.luvora.ai.dto.member;

import com.luvora.ai.enums.ProjectRole;

public record InviteMemberRequest(
        String email,
        ProjectRole role
) {
}

