package com.habsida.morago.model.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import java.time.LocalDateTime;

@Entity
@Table(name="user_profiles")
@Setter
@Getter
@EqualsAndHashCode
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="is_free_call_made")
    private Boolean isFreeCallMade;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @OneToOne(mappedBy = "userProfile", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private UserProfile user;
}
