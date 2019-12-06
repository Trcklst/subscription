package com.trcklst.getsubscription.ws.core;

import com.trcklst.getsubscription.api.GetSubscriptionDto;
import com.trcklst.getsubscription.ws.core.exceptions.NoSubscriptionForUserIdException;
import com.trcklst.getsubscription.ws.core.db.SubscriptionEntity;
import com.trcklst.getsubscription.ws.core.db.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetSubscriptionService {

    private final GetSubscriptionMapper getSubscriptionMapper;
    private final SubscriptionRepository subscriptionRepository;

    public GetSubscriptionDto process(Integer userId) throws NoSubscriptionForUserIdException {
        Optional<SubscriptionEntity> subscriptionEntity = subscriptionRepository.findFirstByUserIdOrderByEndDateDesc(userId);
        return subscriptionEntity.map(getSubscriptionMapper::map)
                .orElseThrow(NoSubscriptionForUserIdException::new);
    }
}