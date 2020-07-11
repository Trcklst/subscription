package com.trcklst.subscription.ws;

import com.trcklst.subscription.api.SubscriptionDto;
import com.trcklst.subscription.ws.mock.PostSubscriptionInMock;
import com.trcklst.subscription.ws.mock.SubscriptionEntityMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class PostSubscriptionTest extends SubscriptionApplicationTests {

    private static final String POST_SUBSCRIPTION_URI = "/api/subscription/";

    @Test
    public void newSubscriptionForUserTest() throws Exception {
        Integer userId = getUserIdWithoutSubscription();
        String response = mockMvc.perform(post(POST_SUBSCRIPTION_URI)
                .header("userId", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PostSubscriptionInMock.IN)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        SubscriptionDto subscriptionDto = objectMapper.readValue(response, SubscriptionDto.class);
        Assertions.assertThat(subscriptionDto).isNotNull();
        Assertions.assertThat(subscriptionDto).hasNoNullFieldsOrProperties();
        Assertions.assertThat(subscriptionDto.getUserId()).isEqualTo(userId);
        Assertions.assertThat(subscriptionDto.getEndDate()).isBeforeOrEqualTo(LocalDateTime.now().plusMonths(1));
    }

    @Test
    public void extendSubscriptionDurationTest() throws Exception {
        String response = mockMvc.perform(post(POST_SUBSCRIPTION_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .header("userId", SubscriptionEntityMock.SUBSCRIPTION_PREMIUM.getUserId())
                .content(objectMapper.writeValueAsString(PostSubscriptionInMock.IN)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        SubscriptionDto subscriptionDto = objectMapper.readValue(response, SubscriptionDto.class);
        Assertions.assertThat(subscriptionDto).isNotNull();
        Assertions.assertThat(subscriptionDto).hasNoNullFieldsOrProperties();
        Assertions.assertThat(subscriptionDto.getEndDate()).isEqualToIgnoringNanos(SubscriptionEntityMock.SUBSCRIPTION_PREMIUM.getEndDate().plusMonths(1));
    }
}
