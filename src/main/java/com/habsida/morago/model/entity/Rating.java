package com.habsida.morago.model.entity;


import javax.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "who_user_id", nullable = false)
    private User whoUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_whom_user_id", nullable = false)
    private User toWhomUser;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "grade")
    private Double ratings;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;
}