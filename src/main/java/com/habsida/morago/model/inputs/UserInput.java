package com.habsida.morago.model.inputs;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class UserInput {
    private String phone;
    private String password;
    private String firstName;
    private String lastName;
    private List<String> roles;
}