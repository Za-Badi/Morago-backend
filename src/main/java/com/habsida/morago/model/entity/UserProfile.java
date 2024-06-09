package com.habsida.morago.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name="user_profiles")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="is_free_call_made")
    private Boolean isFreeCallMade;
    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @OneToOne(mappedBy = "userProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
}