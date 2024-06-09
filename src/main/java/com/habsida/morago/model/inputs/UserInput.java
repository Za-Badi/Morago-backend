package com.habsida.morago.model.inputs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserInput {
    private String phone;
    private String password;
    private String firstName;
    private String lastName;
    private List<String> roles;
}