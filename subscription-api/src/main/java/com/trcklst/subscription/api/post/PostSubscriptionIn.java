package com.trcklst.subscription.api.post;

import com.trcklst.subscription.api.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PostSubscriptionIn {

    @NotNull
    private Integer userId;
    @NotNull
    @Valid
    private CreditCardIn creditCard;
    @NotNull
    private SubscriptionType subscriptionType;
}
