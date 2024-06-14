package com.habsida.morago.model.inputs;

import com.habsida.morago.model.enums.QuestionsCategories;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
public class CreateFrequentlyAskedQuestionsInput {
    private String question = "";
    private String answer = "";
    @Enumerated(EnumType.STRING)
    private QuestionsCategories Category;
}
