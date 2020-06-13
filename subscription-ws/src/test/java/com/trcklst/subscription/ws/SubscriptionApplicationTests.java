package com.trcklst.subscription.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trcklst.subscription.ws.configurations.DatabaseTestConfiguration;
import com.trcklst.subscription.ws.db.SubscriptionEntity;
import com.trcklst.subscription.ws.db.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext
@Import(DatabaseTestConfiguration.class)
class SubscriptionApplicationTests {

    @Autowired
    protected WebApplicationContext wac;
    @Autowired
    protected SubscriptionRepository subscriptionRepository;

    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper;

    @PostConstruct
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    protected Integer getUserIdWithoutSubscription() {
        List<Integer> userIdWithSubscription = subscriptionRepository.findAll().stream()
                .map(SubscriptionEntity::getUserId)
                .collect(Collectors.toList());
        return new Random().ints()
                .filter(i -> !userIdWithSubscription.contains(i))
                .findFirst()
                .orElseThrow();
    }

}
