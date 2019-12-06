package com.trcklst.getsubscription.core.db;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Document("subscriptions")
public class Subscription {

    @Id
    private Integer id;
    @Field
    private SubscriptionType type;
    @Field
    private LocalDate startDate;
    @Field
    private LocalDate endDate;
    @Field
    private Integer userId;
}
