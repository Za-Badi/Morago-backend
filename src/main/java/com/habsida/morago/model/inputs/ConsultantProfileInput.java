package com.habsida.morago.model.inputs;

import lombok.Data;

import java.util.List;

@Data
public class ConsultantProfileInput {
    private String dateOfBirth;
    private String email;
    private Boolean isAvailable;
    private Boolean isOnline;
    private String specialization;
    private List<Long> languages;
}
