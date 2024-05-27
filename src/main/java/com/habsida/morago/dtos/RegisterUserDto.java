package com.habsida.morago.dtos;

import com.habsida.morago.model.entity.Files;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserDto {
    private String phone;
    private String password;
}
