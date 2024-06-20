package com.habsida.morago.model.inputs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDebtorInput {
    private String accountHolder;
    private String nameOfBank;
    private Boolean isPaid;
    private Long userId;
}