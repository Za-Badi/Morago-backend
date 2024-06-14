package com.habsida.morago.model.entity;

import com.habsida.morago.model.enums.CallStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "calls")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caller_id", nullable = false)
    private User caller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "duration")
    private Integer duration;

    @Enumerated(EnumType.STRING)
    private CallStatus status;

    @Column(name = "sum")
    private Double sum;

    @Column(name = "commission")
    private Double commission;

    //    @Column(name = "translator_has_rated", columnDefinition = "bit(1)")
    @Column(name = "translator_has_rated")
    private Boolean translatorHasRated;
    //    @Column(name = "user_has_rated", columnDefinition = "bit(1)")
    @Column(name = "user_has_rated")
    private Boolean userHasRated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", nullable = true)
    private Theme theme;
}