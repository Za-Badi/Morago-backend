package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.FrequentlyAskedQuestion;
import com.habsida.morago.model.inputs.CreateFrequentlyAskedQuestionsInput;
import com.habsida.morago.model.inputs.UpdateFrequentlyAskedQuestionsInput;
import com.habsida.morago.serviceImpl.FrequentlyAskedQuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
@RequiredArgsConstructor
public class FrequentlyAskedQuestionsResolver {
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

    public Set<FrequentlyAskedQuestion> getAllFrequentlyAskedQuestionById() {
        return frequentlyAskedQuestionsService.getAllFrequentlyAskedQuestions();
    }

    public FrequentlyAskedQuestion getFrequentlyAskedQuestionById(Long id) {
        return frequentlyAskedQuestionsService.getFrequentlyAskedQuestionByID(id);
    }

}
