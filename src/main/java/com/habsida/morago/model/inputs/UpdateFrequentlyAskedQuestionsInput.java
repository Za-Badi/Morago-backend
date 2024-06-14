package com.habsida.morago.model.inputs;

import com.habsida.morago.model.enums.QuestionsCategories;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Setter
@Getter
public class UpdateFrequentlyAskedQuestionsInput {
    private Long id;
    private String question = "";
    private String answer = "";
    @Enumerated(EnumType.STRING)
    private QuestionsCategories Category;
}
