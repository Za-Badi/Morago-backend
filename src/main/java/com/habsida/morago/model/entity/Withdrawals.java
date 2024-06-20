package com.habsida.morago.model.entity;

import com.habsida.morago.model.enums.PaymentStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "withdrawals")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Withdrawals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", length = 200)
    private String accountNumber;

    @Column(name = "account_holder", length = 200)
    private String accountHolder;


    @Column(name = "sum")
    private Float sum;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private PaymentStatus status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}