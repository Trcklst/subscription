package com.trcklst.subscription.api.billing;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BillingItem {

    private LocalDateTime date;
    private Double price;
    private String invoice;
}
