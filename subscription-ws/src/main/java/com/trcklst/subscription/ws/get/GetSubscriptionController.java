package com.trcklst.subscription.ws.get;

import com.trcklst.subscription.api.SubscriptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscription/")
public class GetSubscriptionController {

    private final GetSubscriptionService getSubscriptionService;

    @GetMapping
    public SubscriptionDto getSubscription() {
        return getSubscriptionService.process();
    }
}
