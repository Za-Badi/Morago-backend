package com.habsida.morago.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@Table(name = "calls")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @Column(name = "status", length = 50)
    private String status;

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