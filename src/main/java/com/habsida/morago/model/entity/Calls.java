package com.habsida.morago.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Entity
@Table(name = "calls")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Calls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}








