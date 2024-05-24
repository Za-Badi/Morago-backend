package com.habsida.morago.model.entity;

import com.habsida.morago.model.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "debtors")
@Data
public class Debtor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_holder", length = 200)
    private String account_holder;

    @Column(name = "name_of_bank", length = 200)
    private String name_of_bank;

//    @Column(name = "isPaid", columnDefinition = "bit(1)")
    @Column(name = "isPaid")
    private Boolean isPaid;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
