package com.habsida.morago.model.inputs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.habsida.morago.model.enums.ESort;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(onConstructor = @__(@JsonCreator))
public class PagingInput {
    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private String sortBy = "id";
    private ESort sort = ESort.DES;
}
