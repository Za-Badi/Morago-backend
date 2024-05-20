package com.habsida.morago.model.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="languages")
@Setter
@Getter
@EqualsAndHashCode
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="name", length = 200)
    private String name;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @ManyToMany(mappedBy = "languages", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TranslatorProfile> translatorProfiles;
}
