package com.trcklst.subscription.api.post;

import com.trcklst.subscription.api.SubscriptionType;
import lombok.Data;

@Data
public class PostSubscriptionIn {

    private Integer userId;
    private CreditCardIn creditCard;
    private SubscriptionType subscriptionType;
}
