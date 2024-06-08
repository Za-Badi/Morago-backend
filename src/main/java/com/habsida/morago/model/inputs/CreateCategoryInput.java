package com.habsida.morago.model.inputs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryInput {
    private String name="";
    private Boolean isActive = false;
}
