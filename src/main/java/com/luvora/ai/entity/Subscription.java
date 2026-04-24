package com.luvora.ai.entity;

import com.luvora.ai.enums.SubstriptionStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subscription {
    Long id;
    User user;
    Plan plan;
    SubstriptionStatus substriptionStatus;
    String stripeCustomerId;
    String stripeSubscriptionId;
    Instant currentPeriodStart;
    Instant currentPeriodEnd;
    Boolean cancelAtPeriodEnd;
    Instant createdAt;
    Instant updatedAt;
}
