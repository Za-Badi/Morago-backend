package com.habsida.morago.exceptions;

import com.habsida.morago.model.enums.CallStatus;

public class CallStatusNotFoundException extends RuntimeException{
    public CallStatusNotFoundException(CallStatus callStatus) {
        super("no call found");
    }
}
