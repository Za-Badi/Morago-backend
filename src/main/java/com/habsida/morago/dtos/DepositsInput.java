package com.habsida.morago.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositsInput {
    private String accountHolder;
    private String nameOfBank;
    private Double coin;
    private Double won;
    private String status;
    private Long userId;
}
