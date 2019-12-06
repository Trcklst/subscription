package com.trcklst.getsubscription.ws.getsubscription.core.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SubscriptionRepository extends MongoRepository<SubscriptionEntity, Integer> {

    Optional<SubscriptionEntity> findFirstByUserIdOrderByEndDateDesc(Integer userId);
}
