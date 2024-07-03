package com.habsida.morago.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "translator_profiles")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class TranslatorProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "date_of_birth", length = 50)
    private String dateOfBirth;
    @Column(name = "email", length = 255)
    private String email;
    @Column(name = "is_available")
    private Boolean isAvailable;
    @Column(name = "is_online")
    private Boolean isOnline;
    @Column(name = "level_of_korean", length = 200)
    private String levelOfKorean;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "translator_languages",
            joinColumns = @JoinColumn(name = "translator_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<Language> languages;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "translator_themes",
            joinColumns = @JoinColumn(name = "translator_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id"))
    private List<Theme> themes;
    @OneToOne(mappedBy = "translatorProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
}
