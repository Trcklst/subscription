package com.trcklst.subscription.ws;

import com.trcklst.subscription.api.SubscriptionDto;
import com.trcklst.subscription.ws.db.SubscriptionEntity;
import com.trcklst.subscription.ws.mock.SubscriptionEntityMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.temporal.ChronoUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class GetSubscriptionTest extends SubscriptionApplicationTests {

    private static final String GET_SUBSCRIPTION_URI = "/api/subscription/{userId}";

    @Test
    void getWithValidUserIdTest() throws Exception {
        SubscriptionEntity subscriptionEntity = SubscriptionEntityMock.SUBSCRIPTION_PREMIUM;
        String response = mockMvc.perform(get(GET_SUBSCRIPTION_URI, subscriptionEntity.getUserId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        SubscriptionDto subscriptionDto = objectMapper.readValue(response, SubscriptionDto.class);
        Assertions.assertEquals(subscriptionDto.getId(), subscriptionEntity.getId());
        Assertions.assertEquals(subscriptionDto.getStartDate().truncatedTo(ChronoUnit.SECONDS), subscriptionEntity.getStartDate().truncatedTo(ChronoUnit.SECONDS));
        Assertions.assertEquals(subscriptionDto.getType(), subscriptionEntity.getType());
    }

    @Test
    void userIdWithoutSubscriptionTest() throws Exception {
        Integer userIdWithoutSubscription = getUserIdWithoutSubscription();
        mockMvc.perform(get(GET_SUBSCRIPTION_URI, userIdWithoutSubscription))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
