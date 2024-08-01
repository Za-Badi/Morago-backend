package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.dto.FrequentlyAskedQuestionDTO;
import com.habsida.morago.model.enums.QuestionsCategories;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.FrequentlyAskedQuestionPageOutput;
import com.habsida.morago.serviceImpl.FrequentlyAskedQuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FrequentlyAskedQuestionsQueryResolver implements GraphQLQueryResolver {
    private final FrequentlyAskedQuestionsService frequentlyAskedQuestionsService;

    public FrequentlyAskedQuestionPageOutput getAllFrequentlyAskedQuestions(PagingInput pagingInput) {
        return frequentlyAskedQuestionsService.getAllFrequentlyAskedQuestions(pagingInput);
    }

    public FrequentlyAskedQuestionDTO getFrequentlyAskedQuestionById(Long id) {
        return frequentlyAskedQuestionsService.getFrequentlyAskedQuestionByID(id);
    }

    public FrequentlyAskedQuestionPageOutput getAllFrequentlyAskedQuestionsByCategory(PagingInput pagingInput, QuestionsCategories category) {
        return frequentlyAskedQuestionsService.getAllFrequentlyAskedQuestionsByCategory(pagingInput, category);
    }
}
