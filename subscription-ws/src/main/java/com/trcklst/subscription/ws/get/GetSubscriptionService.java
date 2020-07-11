package com.trcklst.subscription.ws.get;

import com.trcklst.subscription.api.SubscriptionDto;
import com.trcklst.subscription.ws.common.db.SubscriptionEntity;
import com.trcklst.subscription.ws.common.db.SubscriptionRepository;
import com.trcklst.subscription.ws.common.exceptions.NoSubscriptionForUserIdException;
import com.trcklst.subscription.ws.common.utils.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetSubscriptionService {

    private final GetSubscriptionMapper getSubscriptionMapper;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionDto process() throws NoSubscriptionForUserIdException {
        Integer userId = RequestUtils.getUserIdFromHeader();
        Optional<SubscriptionEntity> subscriptionEntity = subscriptionRepository.findFirstByUserIdOrderByEndDateDesc(userId);
        return subscriptionEntity.map(getSubscriptionMapper::map)
                .orElseThrow(NoSubscriptionForUserIdException::new);
    }
}
