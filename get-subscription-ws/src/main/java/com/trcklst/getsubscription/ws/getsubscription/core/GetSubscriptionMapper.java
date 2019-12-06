package com.trcklst.getsubscription.ws.getsubscription.core;

import com.trcklst.getsubscription.api.GetSubscriptionDto;
import com.trcklst.getsubscription.ws.getsubscription.core.db.SubscriptionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetSubscriptionMapper {

    GetSubscriptionDto map(SubscriptionEntity subscriptionEntity);
}
