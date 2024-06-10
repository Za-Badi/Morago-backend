package com.habsida.morago.model.inputs;

import com.habsida.morago.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WithdrawalInput {
    private String accountNumber;
    private String accountHolder;
    private Float sum;
    private Status status;
    private Long userId;
}