package com.habsida.morago.model.dto;

import lombok.Data;
import com.habsida.morago.model.enums.QuestionsCategories;
import java.time.LocalDateTime;

@Data
public class FrequentlyAskedQuestionDTO {
    private Long id;
    private String question;
    private String answer;
    private QuestionsCategories category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
