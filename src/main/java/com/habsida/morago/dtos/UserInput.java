package com.habsida.morago.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInput {
    private String phone;
    private String password;
    private String firstName;
    private String lastName;
}