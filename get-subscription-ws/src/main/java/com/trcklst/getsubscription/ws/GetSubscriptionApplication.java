package com.trcklst.getsubscription.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GetSubscriptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetSubscriptionApplication.class, args);
    }

}
