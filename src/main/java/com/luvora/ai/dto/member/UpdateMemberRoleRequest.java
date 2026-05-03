package com.luvora.ai.dto.member;

import com.luvora.ai.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
        @NotNull(message = "Role is required")
        ProjectRole role
) {
}
