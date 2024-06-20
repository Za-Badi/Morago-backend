package com.habsida.morago.exceptions;

public class ThemeNotFoundException extends RuntimeException{
    public ThemeNotFoundException() {
        super("theme not found");
    }
}
