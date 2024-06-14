package com.habsida.morago.model.inputs;

import com.habsida.morago.model.enums.CallStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositsInput {
    private String accountHolder;
    private String nameOfBank;
    private Double coin;
    private Double won;
    private CallStatus status;
    private Long userId;
}