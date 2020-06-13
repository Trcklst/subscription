package com.trcklst.subscription.api;

public enum SubscriptionType {

    PREMIUM(399),
    PRO(999);

    private final long priceInCents;

    SubscriptionType(long priceInCents) {
        this.priceInCents = priceInCents;
    }

    public long getPriceInCents() {
        return priceInCents;
    }
}
