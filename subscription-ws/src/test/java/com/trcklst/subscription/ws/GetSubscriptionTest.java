package com.trcklst.subscription.ws;

import com.trcklst.subscription.api.SubscriptionDto;
import com.trcklst.subscription.ws.mock.SubscriptionEntityMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.temporal.ChronoUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class GetSubscriptionTest extends SubscriptionApplicationTests {

    @Test
    void getWithValidUserIdTest() throws Exception {
        String response = mockMvc.perform(get(SUBSCRIPTION_URI)
                .header("userId", SubscriptionEntityMock.SUBSCRIPTION_PREMIUM.getUserId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        SubscriptionDto subscriptionDto = objectMapper.readValue(response, SubscriptionDto.class);
        Assertions.assertEquals(subscriptionDto.getId(), SubscriptionEntityMock.SUBSCRIPTION_PREMIUM.getId());
        Assertions.assertEquals(subscriptionDto.getStartDate().truncatedTo(ChronoUnit.SECONDS), SubscriptionEntityMock.SUBSCRIPTION_PREMIUM.getStartDate().truncatedTo(ChronoUnit.SECONDS));
        Assertions.assertEquals(subscriptionDto.getType(), SubscriptionEntityMock.SUBSCRIPTION_PREMIUM.getType());
    }

    @Test
    void userIdWithoutSubscriptionTest() throws Exception {
        Integer userIdWithoutSubscription = getUserIdWithoutSubscription();
        mockMvc.perform(get(SUBSCRIPTION_URI)
                .header("userId", userIdWithoutSubscription))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
