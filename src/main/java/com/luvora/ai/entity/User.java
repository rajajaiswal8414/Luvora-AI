package com.luvora.ai.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String passwordHash;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String avatarUrl;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    Instant  createdAt;

    @UpdateTimestamp
    Instant updatedAt;

    Instant deletedAt; //soft delete
}
