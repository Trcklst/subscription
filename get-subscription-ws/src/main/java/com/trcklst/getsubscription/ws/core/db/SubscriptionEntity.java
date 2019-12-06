package com.trcklst.getsubscription.ws.core.db;

import com.trcklst.getsubscription.api.SubscriptionType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document("subscriptions")
public class SubscriptionEntity {

    @Id
    private Integer id;
    @Field
    private SubscriptionType type;
    @Field
    private LocalDateTime startDate;
    @Field
    private LocalDateTime endDate;
    @Field
    private Integer userId;
}
