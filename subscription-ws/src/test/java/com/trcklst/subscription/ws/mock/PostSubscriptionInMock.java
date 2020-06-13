package com.trcklst.subscription.ws.mock;

import com.trcklst.subscription.api.SubscriptionType;
import com.trcklst.subscription.api.post.CreditCardIn;
import com.trcklst.subscription.api.post.PostSubscriptionIn;

import java.time.LocalDate;

public class PostSubscriptionInMock {

    public static final PostSubscriptionIn IN_WITHOUT_USER_ID = createPostSubscriptionInWithoutUserId();

    private static PostSubscriptionIn createPostSubscriptionInWithoutUserId() {
        return PostSubscriptionIn.builder()
                .creditCard(creditCardForTest())
                .subscriptionType(SubscriptionType.PRO)
                .build();
    }

    private static CreditCardIn creditCardForTest() {
        CreditCardIn creditCardIn = new CreditCardIn();
        creditCardIn.setCvc("837");
        creditCardIn.setExpirationMonth(1L);
        creditCardIn.setExpirationYear((long) LocalDate.now().plusYears(1).getYear());
        creditCardIn.setNumber("4242424242424242");
        return creditCardIn;
    }
}
