package com.luvora.ai.service;

import com.luvora.ai.dto.subscription.PlanLimitsResponse;
import com.luvora.ai.dto.subscription.UsageTodayResponse;

public interface UsageService {
    UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
