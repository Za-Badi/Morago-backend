package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.entity.FrequentlyAskedQuestion;
import com.habsida.morago.serviceImpl.FrequentlyAskedQuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class FrequentlyAskedQuestionsQueryResolver implements GraphQLQueryResolver {
    private final FrequentlyAskedQuestionsService frequentlyAskedQuestionsService;

    public Set<FrequentlyAskedQuestion> getAllFrequentlyAskedQuestionById() {
        return frequentlyAskedQuestionsService.getAllFrequentlyAskedQuestions();
    }

    public FrequentlyAskedQuestion getFrequentlyAskedQuestionById(Long id) {
        return frequentlyAskedQuestionsService.getFrequentlyAskedQuestionByID(id);
    }

}
