package com.habsida.morago.model.inputs;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePasswordInput {
    private String originalPassword;
    private String newPassword;
}
