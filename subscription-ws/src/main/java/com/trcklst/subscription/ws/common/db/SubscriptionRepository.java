package com.trcklst.subscription.ws.common.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends MongoRepository<SubscriptionEntity, Integer> {

    Optional<SubscriptionEntity> findFirstByUserIdOrderByEndDateDesc(Integer userId);

    Optional<SubscriptionEntity> findFirstByOrderByIdDesc();

    List<SubscriptionEntity> findAllByUserId(Integer userId);
}
