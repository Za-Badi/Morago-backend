package com.habsida.morago.model.inputs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CallPagingInput {
    private Integer pageNo = 0;
    private Integer pageSize = 10;
}
