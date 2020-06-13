package com.trcklst.subscription.ws.post;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentMethodCreateParams;
import com.trcklst.subscription.api.SubscriptionDto;
import com.trcklst.subscription.api.post.PostSubscriptionIn;
import com.trcklst.subscription.ws.db.SubscriptionEntity;
import com.trcklst.subscription.ws.db.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostSubscriptionService {

    private PostSubscriptionMapper postSubscriptionMapper;
    private SubscriptionRepository subscriptionRepository;

    public SubscriptionDto process(PostSubscriptionIn postSubscriptionIn) throws StripeException {
        PaymentMethod paymentMethod = createPaymentMethod(postSubscriptionIn);
        PaymentIntent paymentIntent = createPaymentIntent(postSubscriptionIn, paymentMethod);
        SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
        subscriptionEntity.setId(postSubscriptionIn.getUserId());
        subscriptionEntity.setEndDate(LocalDateTime.now().plusMonths(1));
        subscriptionEntity.setStartDate(LocalDateTime.now());
        subscriptionEntity.setType(postSubscriptionIn.getSubscriptionType());
        subscriptionEntity.setTransactionId(paymentIntent.getId());

        subscriptionEntity = subscriptionRepository.save(subscriptionEntity);
        return postSubscriptionMapper.map(subscriptionEntity);
    }

    private PaymentMethod createPaymentMethod(PostSubscriptionIn postSubscriptionIn) throws StripeException {
        PaymentMethodCreateParams paymentMethodCreateParams = PaymentMethodCreateParams.builder()
                .setType(PaymentMethodCreateParams.Type.CARD)
                .setCard(PaymentMethodCreateParams.CardDetails.builder()
                        .setNumber(postSubscriptionIn.getCreditCard().getNumber())
                        .setExpMonth(postSubscriptionIn.getCreditCard().getExpirationMonth())
                        .setExpYear(postSubscriptionIn.getCreditCard().getExpirationYear())
                        .setCvc(postSubscriptionIn.getCreditCard().getCvc())
                        .build())
                .build();
        return PaymentMethod.create(paymentMethodCreateParams);
    }

    private PaymentIntent createPaymentIntent(PostSubscriptionIn postSubscriptionIn, PaymentMethod paymentMethod) throws StripeException {
        PaymentIntentCreateParams paymentIntentCreateParams = PaymentIntentCreateParams.builder()
                .setAmount(postSubscriptionIn.getSubscriptionType().getPriceInCents())
                .setCurrency("eur")
                .setConfirm(true)
                .addPaymentMethodType("card")
                .setPaymentMethod(paymentMethod.getId())
                .build();
        return PaymentIntent.create(paymentIntentCreateParams);
    }

    @Autowired
    public void setPostSubscriptionMapper(PostSubscriptionMapper postSubscriptionMapper) {
        this.postSubscriptionMapper = postSubscriptionMapper;
    }

    @Autowired
    public void setSubscriptionRepository(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }
}
