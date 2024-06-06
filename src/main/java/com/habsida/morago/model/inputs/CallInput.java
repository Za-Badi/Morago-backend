package com.habsida.morago.model.inputs;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CallInput {
    private Long callerId;
    private Long recipientId;
    private Long themeId;
}



