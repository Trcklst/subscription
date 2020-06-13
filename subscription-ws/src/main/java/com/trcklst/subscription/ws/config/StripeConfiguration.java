package com.trcklst.subscription.ws.config;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class StripeConfiguration {

    @Value("${stripe.api.key}")
    private String apiKey;

    @PostConstruct
    public void stripeSetup() {
        Stripe.apiKey = apiKey;
    }
}
