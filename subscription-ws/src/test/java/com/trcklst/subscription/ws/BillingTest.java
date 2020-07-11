package com.trcklst.subscription.ws;

import com.trcklst.subscription.api.billing.BillingDto;
import com.trcklst.subscription.ws.mock.SubscriptionEntityMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class BillingTest extends SubscriptionApplicationTests {

    private static final String BILLING_URI = SUBSCRIPTION_URI + "billing/";

    @Test
    void getWithValidUserIdTest() throws Exception {
        String response = mockMvc.perform(get(BILLING_URI)
                .header("userId", SubscriptionEntityMock.SUBSCRIPTION_PREMIUM.getUserId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        BillingDto billingDto = objectMapper.readValue(response, BillingDto.class);
        Assertions.assertThat(billingDto.getBillingItems()).hasSize(1);
    }
}
