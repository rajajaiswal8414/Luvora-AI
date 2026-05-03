package com.luvora.ai.entity;

import com.luvora.ai.enums.MessageRole;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    ChatSession chatSession;
    String content;
    MessageRole role;
    String toolCalls; // JSON Array of tools called
    Integer tokenUsed;
    Instant createdAt;
}
