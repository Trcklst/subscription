package com.trcklst.getsubscription.ws.configurations;

import com.trcklst.getsubscription.ws.core.db.SubscriptionRepository;
import com.trcklst.getsubscription.ws.mock.SubscriptionEntityMock;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@RequiredArgsConstructor
public class DatabaseTestConfiguration {

    private final SubscriptionRepository subscriptionRepository;

    @Bean
    public CommandLineRunner initDb() {
        return args -> {
            subscriptionRepository.save(SubscriptionEntityMock.SUBSCRIPTION_PRO);
            subscriptionRepository.save(SubscriptionEntityMock.SUBSCRIPTION_PREMIUM);
        };
    }
}