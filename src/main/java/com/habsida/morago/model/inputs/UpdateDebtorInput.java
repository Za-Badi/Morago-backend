package com.habsida.morago.model.inputs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDebtorInput {
    private String accountHolder;
    private String nameOfBank;
    private Boolean isPaid;
}