package com.luvora.ai.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String email;
    String passwordHash;
    String name;
    String avatarUrl;
    Instant  createdAt;
    Instant updatedAt;
    Instant deletedAt; //soft delete
}
