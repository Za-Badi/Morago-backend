package com.habsida.morago.model.inputs;

import com.habsida.morago.model.enums.QuestionsCategories;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UpdateFrequentlyAskedQuestionsInput {
    private Long id;
    private String question = "";
    private String answer = "";
    @Enumerated(EnumType.STRING)
    private QuestionsCategories Category;
}
