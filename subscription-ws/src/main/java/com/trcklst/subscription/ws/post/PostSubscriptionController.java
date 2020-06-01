package com.trcklst.subscription.ws.post;

import com.trcklst.subscription.api.SubscriptionDto;
import com.trcklst.subscription.api.post.PostSubscriptionIn;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscription")
public class PostSubscriptionController {

    private PostSubscriptionService postSubscriptionService;

    @PostMapping
    public SubscriptionDto postSubscription(PostSubscriptionIn postSubscriptionIn) {
        return postSubscriptionService.process(postSubscriptionIn);
    }
}
