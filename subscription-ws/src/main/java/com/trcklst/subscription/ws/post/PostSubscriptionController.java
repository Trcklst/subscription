package com.trcklst.subscription.ws.post;

import com.stripe.exception.StripeException;
import com.trcklst.subscription.api.SubscriptionDto;
import com.trcklst.subscription.api.post.PostSubscriptionIn;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscription")
public class PostSubscriptionController {

    private PostSubscriptionService postSubscriptionService;

    @PostMapping
    public SubscriptionDto postSubscription(@RequestBody PostSubscriptionIn postSubscriptionIn) throws StripeException {
        return postSubscriptionService.process(postSubscriptionIn);
    }

    @Autowired
    public void setPostSubscriptionService(PostSubscriptionService postSubscriptionService) {
        this.postSubscriptionService = postSubscriptionService;
    }
}
