package com.habsida.morago.model.inputs;

import com.habsida.morago.model.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDepositsInput {
    private String accountHolder;
    private String nameOfBank;
    private Double coin;
    private Double won;
    private PaymentStatus status;

}