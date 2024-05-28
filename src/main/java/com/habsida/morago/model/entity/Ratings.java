package com.habsida.morago.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Ratings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "who_user_id", nullable = false)
    private User whoUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_whom_user_id", nullable = false)
    private User toWhomUser;

    @Column(name = "grade")
    private Double grade;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "ratings")
    private Double ratings;

    @Column(name = "total_ratings")
    private Integer totalRatings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private Files file;

}