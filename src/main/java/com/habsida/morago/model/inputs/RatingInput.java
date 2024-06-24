package com.habsida.morago.model.inputs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingInput {
    private Long whoUserId;
    private Long toWhomUserId;
    private Double ratings;
}