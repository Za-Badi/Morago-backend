package com.habsida.morago.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDateTime;

@Entity
@Table(name = "password_resets")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PasswordReset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    private String token;
    private Integer resetCode;

    @CreatedDate
    @Column(updatable = false)
//    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @LastModifiedDate
//    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime updatedAt;

}
