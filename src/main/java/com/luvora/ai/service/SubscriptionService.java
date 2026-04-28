package com.luvora.ai.service;

import com.luvora.ai.dto.subscription.CheckoutRequest;
import com.luvora.ai.dto.subscription.CheckoutResponse;
import com.luvora.ai.dto.subscription.PortalResponse;
import com.luvora.ai.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userId);

    CheckoutResponse createStripeCheckoutSessionUrl(CheckoutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}
