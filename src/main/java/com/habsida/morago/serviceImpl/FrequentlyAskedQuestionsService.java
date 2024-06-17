package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.FrequentlyAskedQuestion;
import com.habsida.morago.model.enums.QuestionsCategories;
import com.habsida.morago.model.inputs.CreateFrequentlyAskedQuestionsInput;
import com.habsida.morago.model.inputs.UpdateFrequentlyAskedQuestionsInput;
import com.habsida.morago.repository.FrequentlyAskedQuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FrequentlyAskedQuestionsService {
    private final FrequentlyAskedQuestionsRepository repository;

    public FrequentlyAskedQuestion createFrequentlyAskedQuestion(CreateFrequentlyAskedQuestionsInput input) {
        FrequentlyAskedQuestion frequentlyAskedQuestion = new FrequentlyAskedQuestion();
        frequentlyAskedQuestion.setQuestion(input.getQuestion());
        frequentlyAskedQuestion.setAnswer(input.getAnswer());
        frequentlyAskedQuestion.setCategory(input.getCategory());
        return repository.save(frequentlyAskedQuestion);
    }

    public FrequentlyAskedQuestion getFrequentlyAskedQuestionByID(Long id) {
        return repository.findById(id).orElseThrow(()-> new EntityNotFoundException("No FAQ with id: "+ id));
    }

    public FrequentlyAskedQuestion updateFrequentlyAskedQuestion(UpdateFrequentlyAskedQuestionsInput input) {
        FrequentlyAskedQuestion frequentlyAskedQuestion = repository.findById(input.getId()).orElseThrow(EntityNotFoundException::new);
        if (input.getQuestion() != null && !input.getQuestion().isEmpty()) {
            frequentlyAskedQuestion.setQuestion(input.getQuestion());
        }
        if (input.getAnswer() != null && !input.getAnswer().isEmpty()) {
            frequentlyAskedQuestion.setAnswer(input.getAnswer());
        }
        if (input.getCategory() != null) {
            frequentlyAskedQuestion.setCategory(input.getCategory());
        }
        return repository.save(frequentlyAskedQuestion);
    }

    public Set<FrequentlyAskedQuestion> getAllFrequentlyAskedQuestions() {
        return new HashSet<>(repository.findAll());
    }

    public Boolean deleteFrequentlyAskedQuestionsById(Long id) {
        FrequentlyAskedQuestion frequentlyAskedQuestion = getFrequentlyAskedQuestionByID(id);
        repository.delete(frequentlyAskedQuestion);
        return true;
    }

    public Set<FrequentlyAskedQuestion> getFAQByCategory(QuestionsCategories questionsCategories) {
        return repository.findFrequentlyAskedQuestionByCategory(questionsCategories);
    }
}
