package com.trcklst.subscription.ws.get;

import com.trcklst.subscription.api.SubscriptionDto;
import com.trcklst.subscription.ws.get.exceptions.NoSubscriptionForUserIdException;
import com.trcklst.subscription.ws.db.SubscriptionEntity;
import com.trcklst.subscription.ws.db.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetSubscriptionService {

    private final GetSubscriptionMapper getSubscriptionMapper;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionDto process(Integer userId) throws NoSubscriptionForUserIdException {
        Optional<SubscriptionEntity> subscriptionEntity = subscriptionRepository.findFirstByUserIdOrderByEndDateDesc(userId);
        return subscriptionEntity.map(getSubscriptionMapper::map)
                .orElseThrow(NoSubscriptionForUserIdException::new);
    }
}
