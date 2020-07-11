package com.trcklst.subscription.api.billing;

import lombok.Data;

import java.util.List;

@Data
public class BillingDto {

    List<BillingItem> billingItems;
}
