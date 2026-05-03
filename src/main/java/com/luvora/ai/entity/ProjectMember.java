package com.luvora.ai.entity;

import com.luvora.ai.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "project_members")
public class ProjectMember {
    @EmbeddedId
    ProjectMemberId id;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id", nullable = false)
    Project project;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ProjectRole projectRole;

    Instant invitedAt;
    Instant acceptedAt;
}
