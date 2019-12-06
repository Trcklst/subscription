package com.trcklst.getsubscription.core;

import com.trcklst.getsubscription.core.db.Subscription;
import com.trcklst.getsubscription.core.db.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public Subscription process(Integer userId) {
        return subscriptionRepository.findFirstByUserIdOrderByEndDateDesc(userId);
    }
}
