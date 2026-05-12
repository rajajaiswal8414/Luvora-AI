package com.luvora.ai.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.luvora.ai.enums.ProjectPermission.*;

@RequiredArgsConstructor
@Getter
public enum ProjectRole {
    EDITOR(Set.of(EDIT, VIEW)),
    VIEWER(Set.of(VIEW)),
    OWNER(Set.of(EDIT, VIEW, DELETE, MANAGE_MEMBERS)),
    ;

    private final Set<ProjectPermission> permissions;
}
