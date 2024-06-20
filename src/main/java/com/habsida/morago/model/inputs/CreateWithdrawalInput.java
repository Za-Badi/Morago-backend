package com.habsida.morago.model.inputs;

import com.habsida.morago.model.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateWithdrawalInput {
    private String accountNumber;
    private String accountHolder;
    private Float sum;
    private PaymentStatus status;
    private Long userId;
}