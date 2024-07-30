package com.habsida.morago.repository;

import com.habsida.morago.model.entity.FrequentlyAskedQuestion;
import com.habsida.morago.model.enums.QuestionsCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrequentlyAskedQuestionsRepository extends JpaRepository<FrequentlyAskedQuestion, Long> {
    Page<FrequentlyAskedQuestion> findFrequentlyAskedQuestionByCategory(QuestionsCategories questionsCategories, Pageable pageable);
}
