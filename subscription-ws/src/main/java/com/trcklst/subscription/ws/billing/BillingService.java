package com.trcklst.subscription.ws.billing;

import com.trcklst.subscription.api.billing.BillingDto;
import com.trcklst.subscription.api.billing.BillingItem;
import com.trcklst.subscription.ws.common.db.SubscriptionEntity;
import com.trcklst.subscription.ws.common.db.SubscriptionRepository;
import com.trcklst.subscription.ws.common.utils.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillingService {

    private final SubscriptionRepository subscriptionRepository;

    public BillingDto process() {
        Integer userId = RequestUtils.getUserIdFromHeader();
        List<SubscriptionEntity> subscriptionEntities = subscriptionRepository.findAllByUserId(userId);

        List<BillingItem> billingItems = subscriptionEntities.stream()
                .filter(Objects::nonNull)
                .map(this::mapItem)
                .collect(Collectors.toList());

        BillingDto billingDto = new BillingDto();
        billingDto.setBillingItems(billingItems);
        return billingDto;
    }

    private BillingItem mapItem(SubscriptionEntity subscriptionEntity) {
        return BillingItem.builder()
                .price(formatPriceInEuro(subscriptionEntity))
                .invoice(subscriptionEntity.getInvoice())
                .date(subscriptionEntity.getStartDate())
                .build();
    }

    private Double formatPriceInEuro(SubscriptionEntity subscriptionEntity) {
        double priceInCents = (double) subscriptionEntity.getType().getPriceInCents();
        return priceInCents / 100;
    }
}
