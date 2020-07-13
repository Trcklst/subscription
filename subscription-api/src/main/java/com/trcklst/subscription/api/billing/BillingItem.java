package com.trcklst.subscription.api.billing;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BillingItem {

    private LocalDateTime date;
    private LocalDateTime expiration;
    private Double price;
    private String invoice;
}
