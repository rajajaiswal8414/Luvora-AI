package com.luvora.ai.dto.subscription;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CheckoutRequest(
        @NotNull(message = "Plan ID is required")
        @Positive(message = "Plan ID must be a positive number")
        Long planId
) {
}
