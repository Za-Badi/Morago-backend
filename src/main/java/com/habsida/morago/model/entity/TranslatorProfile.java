package com.habsida.morago.model.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="translator_profiles")
@Setter
@Getter
@EqualsAndHashCode
public class TranslatorProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="date_of_birth", length = 50)
    private String dateOfBirth;
    @Column(name="email", length = 255)
    private String email;
    @Column(name="is_available")
    private Boolean isAvailable;
    @Column(name="is_online")
    private Boolean isOnline;
    @Column(name="level_of_korean", length = 200)
    private String levelOfKorean;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "translator_languages",
            joinColumns = @JoinColumn(name = "translator_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<Language> languages;
    @OneToOne(mappedBy = "translatorProfile", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;
}
