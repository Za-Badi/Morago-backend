package com.habsida.morago.model.inputs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class NotificationInput {

    private String title;
    private String text;
    private Long userId;
}