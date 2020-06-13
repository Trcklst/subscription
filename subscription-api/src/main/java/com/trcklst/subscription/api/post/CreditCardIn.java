package com.trcklst.subscription.api.post;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreditCardIn {

    @NotBlank
    private String number;
    @NotNull
    @Max(12)
    private Long expirationMonth;
    @NotNull
    private Long expirationYear;
    @NotBlank
    private String cvc;

}
