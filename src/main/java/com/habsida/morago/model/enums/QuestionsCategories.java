package com.habsida.morago.model.enums;

import lombok.Getter;

@Getter
public enum QuestionsCategories {

    Restaurant("Ресторан"),
    Law("Ресторан"),
    ENTERTAINMENT("Развлечения"),
    GROCERY("Сотовая связь");

    private final String name;

    QuestionsCategories(String name) {
        this.name = name;
    }


}
