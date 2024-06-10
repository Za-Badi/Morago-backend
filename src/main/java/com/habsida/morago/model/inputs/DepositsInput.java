package com.habsida.morago.model.input;

import com.habsida.morago.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositsInput {
    private String accountHolder;
    private String nameOfBank;
    private Double coin;
    private Double won;
    private Status status;
    private Long userId;
}