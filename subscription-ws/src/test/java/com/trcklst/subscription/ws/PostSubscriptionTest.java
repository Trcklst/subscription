package com.trcklst.subscription.ws;

import com.trcklst.subscription.api.SubscriptionDto;
import com.trcklst.subscription.api.post.PostSubscriptionIn;
import com.trcklst.subscription.ws.mock.PostSubscriptionInMock;
import com.trcklst.subscription.ws.mock.SubscriptionEntityMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class PostSubscriptionTest extends SubscriptionApplicationTests {

    private static final String POST_SUBSCRIPTION_URI = "/api/subscription";

    @Test
    public void missingMandatoryUserId() throws Exception {
        mockMvc.perform(post(POST_SUBSCRIPTION_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PostSubscriptionInMock.IN_WITHOUT_USER_ID)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void subscriptionForUserTest() throws Exception {
        Integer userId = getUserIdWithoutSubscription();
        PostSubscriptionIn inWithNewUser = PostSubscriptionInMock.IN_WITHOUT_USER_ID.toBuilder()
                .userId(userId)
                .build();
        String response = mockMvc.perform(post(POST_SUBSCRIPTION_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inWithNewUser)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        SubscriptionDto subscriptionDto = objectMapper.readValue(response, SubscriptionDto.class);
        Assertions.assertThat(subscriptionDto).isNotNull();
        Assertions.assertThat(subscriptionDto).hasNoNullFieldsOrProperties();
        Assertions.assertThat(subscriptionDto.getUserId()).isEqualTo(userId);
    }

    @Test
    public void extendSubscriptionDurationTest() throws Exception {
        PostSubscriptionIn inWithNewUser = PostSubscriptionInMock.IN_WITHOUT_USER_ID.toBuilder()
                .userId(SubscriptionEntityMock.SUBSCRIPTION_PREMIUM.getUserId())
                .build();
        String response = mockMvc.perform(post(POST_SUBSCRIPTION_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inWithNewUser)))
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
