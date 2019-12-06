package com.trcklst.getsubscription.ws.core;

import com.trcklst.getsubscription.api.GetSubscriptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscription/{userId}")
public class GetSubscriptionController {

    private final GetSubscriptionService getSubscriptionService;

    @GetMapping
    public GetSubscriptionDto getSubscription(@PathVariable Integer userId) {
        return getSubscriptionService.process(userId);
    }
}
