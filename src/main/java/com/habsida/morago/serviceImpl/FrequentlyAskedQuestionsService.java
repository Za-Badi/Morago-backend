package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.dto.FrequentlyAskedQuestionDTO;
import com.habsida.morago.model.entity.FrequentlyAskedQuestion;
import com.habsida.morago.model.enums.QuestionsCategories;
import com.habsida.morago.model.inputs.CreateFrequentlyAskedQuestionsInput;
import com.habsida.morago.model.inputs.UpdateFrequentlyAskedQuestionsInput;
import com.habsida.morago.repository.FrequentlyAskedQuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FrequentlyAskedQuestionsService {
    private final FrequentlyAskedQuestionsRepository repository;
    private final ModelMapper modelMapper;

    public FrequentlyAskedQuestionDTO createFrequentlyAskedQuestion(CreateFrequentlyAskedQuestionsInput input) {
        FrequentlyAskedQuestion frequentlyAskedQuestion = new FrequentlyAskedQuestion();
        frequentlyAskedQuestion.setQuestion(input.getQuestion());
        frequentlyAskedQuestion.setAnswer(input.getAnswer());
        frequentlyAskedQuestion.setCategory(input.getCategory());
        FrequentlyAskedQuestion savedFAQ = repository.save(frequentlyAskedQuestion);
        return modelMapper.map(savedFAQ, FrequentlyAskedQuestionDTO.class);
    }

    public FrequentlyAskedQuestionDTO getFrequentlyAskedQuestionByID(Long id) {
        FrequentlyAskedQuestion frequentlyAskedQuestion = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No FAQ with id: " + id));
        return modelMapper.map(frequentlyAskedQuestion, FrequentlyAskedQuestionDTO.class);
    }

    public FrequentlyAskedQuestionDTO updateFrequentlyAskedQuestion(UpdateFrequentlyAskedQuestionsInput input) {
        FrequentlyAskedQuestion frequentlyAskedQuestion = repository.findById(input.getId())
                .orElseThrow(EntityNotFoundException::new);
        if (input.getQuestion() != null && !input.getQuestion().isEmpty()) {
            frequentlyAskedQuestion.setQuestion(input.getQuestion());
        }
        if (input.getAnswer() != null && !input.getAnswer().isEmpty()) {
            frequentlyAskedQuestion.setAnswer(input.getAnswer());
        }
        if (input.getCategory() != null) {
            frequentlyAskedQuestion.setCategory(input.getCategory());
        }
        FrequentlyAskedQuestion updatedFAQ = repository.save(frequentlyAskedQuestion);
        return modelMapper.map(updatedFAQ, FrequentlyAskedQuestionDTO.class);
    }

    public Set<FrequentlyAskedQuestionDTO> getAllFrequentlyAskedQuestions() {
        return repository.findAll().stream()
                .map(faq -> modelMapper.map(faq, FrequentlyAskedQuestionDTO.class))
                .collect(Collectors.toSet());
    }

    public Boolean deleteFrequentlyAskedQuestionsById(Long id) {
        FrequentlyAskedQuestion frequentlyAskedQuestion = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No FAQ with id: " + id));
        repository.delete(frequentlyAskedQuestion);
        return true;
    }

    public Set<FrequentlyAskedQuestionDTO> getFAQByCategory(QuestionsCategories questionsCategories) {
        return repository.findFrequentlyAskedQuestionByCategory(questionsCategories).stream()
                .map(faq -> modelMapper.map(faq, FrequentlyAskedQuestionDTO.class))
                .collect(Collectors.toSet());
    }
}
