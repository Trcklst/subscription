package com.trcklst.subscription.ws.billing;

import com.trcklst.subscription.api.SubscriptionType;
import com.trcklst.subscription.api.billing.BillingItem;
import com.trcklst.subscription.ws.common.db.SubscriptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BillingMapper {

    @Mapping(target = "date", source = "startDate")
    @Mapping(target = "price", source = "type")
    @Mapping(target = "expiration", source = "endDate")
    BillingItem map(SubscriptionEntity subscriptionEntity);

    default Double formatPriceInEuro(SubscriptionType type) {
        return (double) type.getPriceInCents() / 100;
    }
}
