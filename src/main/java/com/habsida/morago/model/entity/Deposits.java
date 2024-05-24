package com.habsida.morago.model.entity;

import com.habsida.morago.model.entity.User;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Deposits")
public class Deposits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_holder", length = 200)
    private String account_holder;

    @Column(name = "name_of_bank", length = 200)
    private String name_of_bank;

    @Column(name = "coin")
    private Double coin;

    @Column(name = "won")
    private Double won;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
