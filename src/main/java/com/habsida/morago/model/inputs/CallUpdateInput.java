package com.habsida.morago.model.inputs;

import com.habsida.morago.model.enums.CallStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CallUpdateInput {
    private Integer duration;
    private CallStatus status;
    private Double commission;
}

