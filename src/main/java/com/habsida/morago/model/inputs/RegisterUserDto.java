package com.habsida.morago.model.inputs;


import lombok.Getter;
import lombok.Setter;

import com.habsida.morago.model.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RegisterUserDto {
    private String phone;
    private String password;
    private List<String> roles;
}
