package com.trcklst.subscription.api.post;

import com.trcklst.subscription.api.SubscriptionType;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class PostSubscriptionIn {

    @NotNull
    private Integer userId;
    @NotNull
    @Valid
    private CreditCardIn creditCard;
    @NotNull
    private SubscriptionType subscriptionType;
}
