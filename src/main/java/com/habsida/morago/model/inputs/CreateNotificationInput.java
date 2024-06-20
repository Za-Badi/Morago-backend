package com.habsida.morago.model.inputs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateNotificationInput {

    private String title;
    private String text;

    private Long userId;
}