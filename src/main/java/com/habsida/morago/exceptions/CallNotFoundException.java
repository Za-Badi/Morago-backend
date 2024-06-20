package com.habsida.morago.exceptions;

public class CallNotFoundException extends RuntimeException{
    public CallNotFoundException(Long id) {
        super("Call with id  " +id+ " not found");
    }
}
