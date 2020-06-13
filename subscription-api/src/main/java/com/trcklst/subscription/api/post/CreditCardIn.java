package com.trcklst.subscription.api.post;

import lombok.Data;

@Data
public class CreditCardIn {

    private String number;
    private Long expirationMonth;
    private Long expirationYear;
    private String cvc;

}
