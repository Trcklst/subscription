package com.trcklst.subscription.ws.billing;

import com.trcklst.subscription.api.billing.BillingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscription/billing/")
public class BillingController {

    private final BillingService billingService;

    @GetMapping
    public BillingDto billing() {
        return billingService.process();
    }
}
