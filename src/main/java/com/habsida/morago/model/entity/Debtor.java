package com.habsida.morago.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "debtors")
@Data
public class Debtor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_holder", length = 200)
    private String accountHolder;

    @Column(name = "name_of_bank", length = 200)
    private String nameOfBank;

    //    @Column(name = "isPaid", columnDefinition = "bit(1)")
    @Column(name = "isPaid")
    private Boolean isPaid;

    @CreationTimestamp

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}