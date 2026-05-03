package com.luvora.ai.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProjectRequest(
        @NotBlank(message = "Project name is required")
        @Size(min = 3, max = 50, message = "Project name must be between 3 and 50 characters")
        String name
) {
}
