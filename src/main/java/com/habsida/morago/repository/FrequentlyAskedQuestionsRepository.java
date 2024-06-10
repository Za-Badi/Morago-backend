package com.habsida.morago.repository;

import com.habsida.morago.model.entity.FrequentlyAskedQuestion;
import com.habsida.morago.model.enums.QuestionsCategories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FrequentlyAskedQuestionsRepository extends JpaRepository<FrequentlyAskedQuestion, Long> {
    Set<FrequentlyAskedQuestion> findFrequentlyAskedQuestionByCategory(QuestionsCategories questionsCategories);
}
