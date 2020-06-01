package com.trcklst.subscription.api;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscriptionDto {

    private Integer id;
    private SubscriptionType type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer userId;

}
