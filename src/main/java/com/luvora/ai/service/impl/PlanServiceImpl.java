package com.luvora.ai.service.impl;

import com.luvora.ai.dto.subscription.PlanResponse;
import com.luvora.ai.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
