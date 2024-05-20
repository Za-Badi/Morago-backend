package com.habsida.morago.model.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "coins")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Coins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String coin;
    private String won;
    @Column(updatable = false)
//    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;
    @LastModifiedDate
//    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime updatedAt;
}
