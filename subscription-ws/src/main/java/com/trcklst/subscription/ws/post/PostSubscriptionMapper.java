package com.trcklst.subscription.ws.post;

import com.trcklst.subscription.api.SubscriptionDto;
import com.trcklst.subscription.ws.common.db.SubscriptionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostSubscriptionMapper {

    SubscriptionDto map(SubscriptionEntity subscriptionEntity);
}
