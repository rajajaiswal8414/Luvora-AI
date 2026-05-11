package com.luvora.ai.service.impl;

import com.luvora.ai.dto.subscription.PlanLimitsResponse;
import com.luvora.ai.dto.subscription.UsageTodayResponse;
import com.luvora.ai.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {

    @Override
    public UsageTodayResponse getTodayUsageOfUser() {
        return null;
    }

    @Override
    public PlanLimitsResponse getCurrentSubscriptionLimitsOfUser() {
        return null;
    }
}
