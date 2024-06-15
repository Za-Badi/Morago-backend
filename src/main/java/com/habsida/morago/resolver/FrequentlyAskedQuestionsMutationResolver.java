package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.model.entity.FrequentlyAskedQuestion;
import com.habsida.morago.model.inputs.CreateFrequentlyAskedQuestionsInput;
import com.habsida.morago.model.inputs.UpdateFrequentlyAskedQuestionsInput;
import com.habsida.morago.serviceImpl.FrequentlyAskedQuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class FrequentlyAskedQuestionsMutationResolver implements GraphQLMutationResolver {
    private final FrequentlyAskedQuestionsService frequentlyAskedQuestionsService;

    public FrequentlyAskedQuestion createFrequentlyAskedQuestion(CreateFrequentlyAskedQuestionsInput input) {
        return frequentlyAskedQuestionsService.createFrequentlyAskedQuestion(input);
    }

    public FrequentlyAskedQuestion updateFrequentlyAskedQuestion(UpdateFrequentlyAskedQuestionsInput input) {
        return frequentlyAskedQuestionsService.updateFrequentlyAskedQuestion(input);
    }

    public Boolean deleteFrequentlyAskedQuestionById(Long id) {
        return frequentlyAskedQuestionsService.deleteFrequentlyAskedQuestionsById(id);
    }
}
