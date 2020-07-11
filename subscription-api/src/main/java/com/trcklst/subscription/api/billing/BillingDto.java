package com.trcklst.subscription.api.billing;

import lombok.Data;

import java.util.List;

@Data
public class BillingDto {

    private List<BillingItem> billingItems;
}
