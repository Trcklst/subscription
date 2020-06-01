package com.trcklst.subscription.ws.get;

import com.trcklst.subscription.api.SubscriptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/get-subscription/{userId}")
public class GetSubscriptionController {

    private final GetSubscriptionService getSubscriptionService;

    @GetMapping
    public SubscriptionDto getSubscription(@PathVariable Integer userId) {
        return getSubscriptionService.process(userId);
    }
}
