package com.habsida.morago.model.inputs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateCategoryInput {
    private Long id;
    private String name = "";
    private Boolean isActive = false;


}
