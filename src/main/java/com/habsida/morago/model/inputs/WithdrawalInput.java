package com.habsida.morago.model.inputs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WithdrawalInput {
    private String accountNumber;
    private String accountHolder;
    private Float sum;
    private String status;
    private Long userId;

}