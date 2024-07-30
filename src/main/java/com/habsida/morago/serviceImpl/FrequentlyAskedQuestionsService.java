package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.dto.FrequentlyAskedQuestionDTO;
import com.habsida.morago.model.entity.FrequentlyAskedQuestion;
import com.habsida.morago.model.enums.QuestionsCategories;
import com.habsida.morago.model.inputs.CreateFrequentlyAskedQuestionsInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.inputs.UpdateFrequentlyAskedQuestionsInput;
import com.habsida.morago.model.results.FrequentlyAskedQuestionPageOutput;
import com.habsida.morago.repository.FrequentlyAskedQuestionsRepository;
import com.habsida.morago.util.ModelMapperUtil;
import com.habsida.morago.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FrequentlyAskedQuestionsService {
    private final FrequentlyAskedQuestionsRepository repository;
    private final ModelMapperUtil modelMapperUtil;

    public FrequentlyAskedQuestionDTO createFrequentlyAskedQuestion(CreateFrequentlyAskedQuestionsInput input) {
        if (input.getQuestion() == null || input.getQuestion().isEmpty() ||
                input.getAnswer() == null || input.getAnswer().isEmpty() ||
                input.getCategory() == null) {
            throw new IllegalArgumentException("Question, answer, and category cannot be null or empty");
        }
        FrequentlyAskedQuestion frequentlyAskedQuestion = new FrequentlyAskedQuestion();
        frequentlyAskedQuestion.setQuestion(input.getQuestion());
        frequentlyAskedQuestion.setAnswer(input.getAnswer());
        frequentlyAskedQuestion.setCategory(input.getCategory());
        FrequentlyAskedQuestion savedFAQ = repository.save(frequentlyAskedQuestion);
        return modelMapperUtil.map(savedFAQ, FrequentlyAskedQuestionDTO.class);
    }

    @Transactional(readOnly = true)
    public FrequentlyAskedQuestionDTO getFrequentlyAskedQuestionByID(Long id) {
        FrequentlyAskedQuestion frequentlyAskedQuestion = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No FAQ with id: " + id));
        return modelMapperUtil.map(frequentlyAskedQuestion, FrequentlyAskedQuestionDTO.class);
    }

    public FrequentlyAskedQuestionDTO updateFrequentlyAskedQuestion(UpdateFrequentlyAskedQuestionsInput input) {
        if (input.getId() == null ||
                (input.getQuestion() != null && input.getQuestion().isEmpty()) ||
                (input.getAnswer() != null && input.getAnswer().isEmpty())) {
            throw new IllegalArgumentException("ID cannot be null, and question/answer cannot be empty if provided");
        }
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
        return modelMapperUtil.map(updatedFAQ, FrequentlyAskedQuestionDTO.class);
    }

    @Transactional(readOnly = true)
    public FrequentlyAskedQuestionPageOutput getAllFrequentlyAskedQuestions(PagingInput pagingInput) {
        Page<FrequentlyAskedQuestion> faqPage = repository.findAll(PageUtil.buildPageable(pagingInput));
        return new FrequentlyAskedQuestionPageOutput(
                faqPage.getNumber(),
                faqPage.getTotalPages(),
                faqPage.getTotalElements(),
                faqPage.getContent().stream()
                        .map(faq -> modelMapperUtil.map(faq, FrequentlyAskedQuestionDTO.class))
                        .collect(Collectors.toList())
        );
    }

    public Boolean deleteFrequentlyAskedQuestionsById(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new EntityNotFoundException("No FAQ with id: " + id);
        }
    }

    @Transactional(readOnly = true)
    public FrequentlyAskedQuestionPageOutput getAllFrequentlyAskedQuestionsByCategory(PagingInput pagingInput, QuestionsCategories questionsCategories) {
        Page<FrequentlyAskedQuestion> faqPage = repository.findFrequentlyAskedQuestionByCategory(questionsCategories, PageUtil.buildPageable(pagingInput));
        return new FrequentlyAskedQuestionPageOutput(
                faqPage.getNumber(),
                faqPage.getTotalPages(),
                faqPage.getTotalElements(),
                faqPage.getContent().stream()
                        .map(faq -> modelMapperUtil.map(faq, FrequentlyAskedQuestionDTO.class))
                        .collect(Collectors.toList())
        );
    }
}
