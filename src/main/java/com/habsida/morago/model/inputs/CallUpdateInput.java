package com.habsida.morago.model.inputs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CallUpdateInput {
    private Integer duration;
    private String status;
    private Double commission;
}

