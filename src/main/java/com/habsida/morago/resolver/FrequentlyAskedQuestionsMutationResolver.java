package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.model.dto.FrequentlyAskedQuestionDTO;
import com.habsida.morago.model.inputs.CreateFrequentlyAskedQuestionsInput;
import com.habsida.morago.model.inputs.UpdateFrequentlyAskedQuestionsInput;
import com.habsida.morago.serviceImpl.FrequentlyAskedQuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FrequentlyAskedQuestionsMutationResolver implements GraphQLMutationResolver {
    private final FrequentlyAskedQuestionsService frequentlyAskedQuestionsService;

    public FrequentlyAskedQuestionDTO createFrequentlyAskedQuestion(CreateFrequentlyAskedQuestionsInput input) {
        return frequentlyAskedQuestionsService.createFrequentlyAskedQuestion(input);
    }

    public FrequentlyAskedQuestionDTO updateFrequentlyAskedQuestion(UpdateFrequentlyAskedQuestionsInput input) {
        return frequentlyAskedQuestionsService.updateFrequentlyAskedQuestion(input);
    }

    public Boolean deleteFrequentlyAskedQuestionById(Long id) {
        return frequentlyAskedQuestionsService.deleteFrequentlyAskedQuestionsById(id);
    }
}
