package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.FrequentlyAskedQuestion;
import com.habsida.morago.model.inputs.CreateFrequentlyAskedQuestionsInput;
import com.habsida.morago.model.inputs.UpdateFrequentlyAskedQuestionsInput;
import com.habsida.morago.serviceImpl.FrequentlyAskedQuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
@RequiredArgsConstructor
public class FrequentlyAskedQuestionsResolver {
    private final FrequentlyAskedQuestionsService frequentlyAskedQuestionsService;

    @MutationMapping
    public FrequentlyAskedQuestion createFrequentlyAskedQuestion(@Argument CreateFrequentlyAskedQuestionsInput input) {
        return frequentlyAskedQuestionsService.createFrequentlyAskedQuestion(input);
    }

    @MutationMapping
    public FrequentlyAskedQuestion updateFrequentlyAskedQuestion(@Argument UpdateFrequentlyAskedQuestionsInput input) {
        return frequentlyAskedQuestionsService.updateFrequentlyAskedQuestion(input);
    }

    @MutationMapping
    public Boolean deleteFrequentlyAskedQuestionById(@Argument Long id) {
        return frequentlyAskedQuestionsService.deleteFrequentlyAskedQuestionsById(id);
    }

    @QueryMapping
    public Set<FrequentlyAskedQuestion> getAllFrequentlyAskedQuestionById() {
        return frequentlyAskedQuestionsService.getAllFrequentlyAskedQuestions();
    }

    @QueryMapping
    public FrequentlyAskedQuestion getFrequentlyAskedQuestionById(@Argument Long id) {
        return frequentlyAskedQuestionsService.getFrequentlyAskedQuestionByID(id);
    }

}
