package com.trcklst.subscription.ws.get;

import com.trcklst.subscription.api.SubscriptionDto;
import com.trcklst.subscription.ws.get.db.SubscriptionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetSubscriptionMapper {

    SubscriptionDto map(SubscriptionEntity subscriptionEntity);
}
