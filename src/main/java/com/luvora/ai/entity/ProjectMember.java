package com.luvora.ai.entity;

import com.luvora.ai.enums.ProjectRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectMember {
    ProjectMemberId projectMemberId;
    Project project;
    User user;
    ProjectRole projectRole;
    Instant invitedAt;
    Instant acceptedAt;
}
