package com.habsida.morago.model.entity;

import com.habsida.morago.model.enums.QuestionsCategories;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "frequently_asked_questions")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FrequentlyAskedQuestions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String answer;
    @Enumerated(EnumType.STRING)
    private QuestionsCategories Category;

    @CreatedDate
    @Column(updatable = false)
//    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @LastModifiedDate
//    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime updatedAt;

}
