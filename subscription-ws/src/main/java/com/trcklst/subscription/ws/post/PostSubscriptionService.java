package com.trcklst.subscription.ws.post;

import com.trcklst.subscription.api.SubscriptionDto;
import com.trcklst.subscription.api.post.PostSubscriptionIn;
import com.trcklst.subscription.ws.db.SubscriptionEntity;
import com.trcklst.subscription.ws.db.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostSubscriptionService {

    private PostSubscriptionMapper postSubscriptionMapper;
    private SubscriptionRepository subscriptionRepository;

    public SubscriptionDto process(PostSubscriptionIn postSubscriptionIn) {
        SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
        subscriptionEntity.setId(postSubscriptionIn.getUserId());
        subscriptionEntity.setEndDate(LocalDateTime.now().plusMonths(1));
        subscriptionEntity.setStartDate(LocalDateTime.now());
        subscriptionEntity.setType(postSubscriptionIn.getSubscriptionType());

        subscriptionEntity = subscriptionRepository.save(subscriptionEntity);
        return postSubscriptionMapper.map(subscriptionEntity);
    }
}
