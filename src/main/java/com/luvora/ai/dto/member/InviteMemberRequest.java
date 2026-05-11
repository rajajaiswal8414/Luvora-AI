package com.luvora.ai.dto.member;

import com.luvora.ai.enums.ProjectRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InviteMemberRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String username,

        @NotNull(message = "Role is required")
        ProjectRole role
) {
}

