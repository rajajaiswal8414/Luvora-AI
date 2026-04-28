package com.luvora.ai.dto.subscription;

import java.time.Instant;

public record SubscriptionResponse(
        PlanResponse planResponse,
        String status,
        Instant periodEnd,
        Long tokensUsedThisCycle
) {
}
