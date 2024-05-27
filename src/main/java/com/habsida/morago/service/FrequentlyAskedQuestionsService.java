package com.habsida.morago.service;



import com.habsida.morago.model.entity.FrequentlyAskedQuestions;
import com.habsida.morago.model.enums.QuestionsCategories;
import com.habsida.morago.repository.FrequentlyAskedQuestionsRespository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FrequentlyAskedQuestionsService {
    private final FrequentlyAskedQuestionsRespository repository;

    public FrequentlyAskedQuestions create(String question, String answer, QuestionsCategories category) {
        FrequentlyAskedQuestions frequentlyAskedQuestions = new FrequentlyAskedQuestions();
        frequentlyAskedQuestions.setQuestion(question);
        frequentlyAskedQuestions.setAnswer(answer);
        frequentlyAskedQuestions.setCategory(category);
        return repository.save(frequentlyAskedQuestions);
    }

    public FrequentlyAskedQuestions getByID(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public FrequentlyAskedQuestions update(Long id, String question, String answer, QuestionsCategories category) {
        FrequentlyAskedQuestions frequentlyAskedQuestions = getByID(id);
        if (question != null && !question.isEmpty()) {
            frequentlyAskedQuestions.setQuestion(question);
        }
        if (answer != null && !answer.isEmpty()) {
            frequentlyAskedQuestions.setAnswer(answer);
        }
        if (category != null) {
            frequentlyAskedQuestions.setCategory(category);
        }
        return repository.save(frequentlyAskedQuestions);
    }

    public List<FrequentlyAskedQuestions> getAll() {
        return repository.findAll();
    }

    public Boolean delete(Long id) {
        FrequentlyAskedQuestions frequentlyAskedQuestions = getByID(id);
        repository.delete(frequentlyAskedQuestions);
        return true;
    }
}
