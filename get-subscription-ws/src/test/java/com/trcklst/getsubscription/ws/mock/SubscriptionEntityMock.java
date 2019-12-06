package com.trcklst.getsubscription.ws.mock;

import com.trcklst.getsubscription.api.SubscriptionType;
import com.trcklst.getsubscription.ws.core.db.SubscriptionEntity;

import java.time.LocalDateTime;

public class SubscriptionEntityMock {

    public static final SubscriptionEntity SUBSCRIPTION_PREMIUM = createSubscriptionPremium();
    public static final SubscriptionEntity SUBSCRIPTION_PRO = createSubscriptionPro();

    private static SubscriptionEntity createSubscriptionPremium() {
        SubscriptionEntity subscription = new SubscriptionEntity();
        subscription.setId(1);
        subscription.setType(SubscriptionType.PREMIUM);
        subscription.setEndDate(LocalDateTime.now().plusMonths(1));
        subscription.setStartDate(LocalDateTime.now().minusMonths(1));
        subscription.setUserId(1);
        return subscription;
    }

    private static SubscriptionEntity createSubscriptionPro() {
        SubscriptionEntity subscription = new SubscriptionEntity();
        subscription.setId(2);
        subscription.setType(SubscriptionType.PRO);
        subscription.setEndDate(LocalDateTime.now().plusMonths(1));
        subscription.setStartDate(LocalDateTime.now().minusMonths(1));
        subscription.setUserId(2);
        return subscription;
    }
}
