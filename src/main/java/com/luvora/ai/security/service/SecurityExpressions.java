package com.luvora.ai.security.service;

import com.luvora.ai.enums.ProjectPermission;
import com.luvora.ai.enums.ProjectRole;
import com.luvora.ai.repository.ProjectMemberRepository;
import com.luvora.ai.security.jwt.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component("security")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityExpressions {

    ProjectMemberRepository projectMemberRepository;
    JwtUtils jwtUtils;

    private boolean hasPermission(
            Long projectId,
            ProjectPermission permission
    ) {

        Long userId = jwtUtils.getCurrentUserId();

        return projectMemberRepository
                .findRoleByProjectIdAndUserId(projectId, userId)
                .map(role ->
                        role.getPermissions().contains(permission)
                )
                .orElse(false);
    }

    public boolean canViewProject(Long projectId){
        return hasPermission(
                projectId,
                ProjectPermission.VIEW
        );
    }

    public boolean canEditProject(Long projectId){
        return hasPermission(
                projectId,
                ProjectPermission.EDIT
        );
    }

    public boolean canDeleteProject(Long projectId) {
        return hasPermission(
                projectId,
                ProjectPermission.DELETE
        );
    }
}
