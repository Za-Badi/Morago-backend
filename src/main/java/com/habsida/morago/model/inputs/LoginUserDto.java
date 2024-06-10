package com.habsida.morago.model.inputs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUserDto {
    private String phone;
    private String password;
}
