package com.trcklst.getsubscription.core.db;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriptionRepository extends MongoRepository<Subscription, Integer> {

    Subscription findFirstByUserIdOrderByEndDateDesc(Integer userId);
}
