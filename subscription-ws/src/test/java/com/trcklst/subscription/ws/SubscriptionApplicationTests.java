package com.trcklst.subscription.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trcklst.subscription.api.SubscriptionDto;
import com.trcklst.subscription.ws.configurations.DatabaseTestConfiguration;
import com.trcklst.subscription.ws.get.db.SubscriptionEntity;
import com.trcklst.subscription.ws.get.db.SubscriptionRepository;
import com.trcklst.subscription.ws.mock.SubscriptionEntityMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ActiveProfiles("test")
@Import(DatabaseTestConfiguration.class)
class SubscriptionApplicationTests {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    private static final String GET_SUBSCRIPTION_URI = "/api/get-subscription/{userId}";
    protected MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @PostConstruct
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void getWithValidUserId() throws Exception {
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
    void userIdWithoutSubscription() throws Exception {
        Integer userIdWithoutSubscription = getUserIdWithoutSubscription();
        mockMvc.perform(get(GET_SUBSCRIPTION_URI, userIdWithoutSubscription))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    private Integer getUserIdWithoutSubscription() {
        List<Integer> userIdWithSubscription = subscriptionRepository.findAll().stream()
                .map(SubscriptionEntity::getUserId)
                .collect(Collectors.toList());
        return new Random().ints()
                .filter(i -> !userIdWithSubscription.contains(i))
                .findFirst()
                .orElseThrow();
    }
}
