package com.habsida.morago.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="phone", nullable = false, length = 100)
    private String phone;
    @Column(name="password", length = 255)
    private String password;
    @Column(name="first_name", length = 200)
    private String firstName;
    @Column(name="last_name", length = 200)
    private String lastName;
    @Column(name="balance")
    private Double balance;
    @Column(name="fcm_token", length = 255)
    private String fcmToken;
    @Column(name="apn_token", length = 255)
    private String apnToken;
    @Column(name="ratings")
    private Double ratings;
    @Column(name="total_ratings")
    private Integer totalRatings;
    @CreatedDate
    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @Column(name="is_active")
    private Boolean isActive;
    @Column(name="is_debtor")
    private Boolean isDebtor;
    @Column(name="on_boarding_status")
    private Integer onBoardingStatus;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="image_id")
    private File image;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="translator_profile_id")
    private TranslatorProfile translatorProfile;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="user_profile_id")
    private UserProfile userProfile;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Deposits> deposits;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Debtor> debtors;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Withdrawals> withdrawals;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Notification> notifications;
    @OneToMany(mappedBy = "whoUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rating> givenRatings;
    @OneToMany(mappedBy = "toWhomUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rating> receivedRatings;
    //    @OneToMany(mappedBy = "recipient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Call> recipientCalls;
//    @OneToMany(mappedBy = "caller", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Call> callerCalls;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
    @Override
    public String getPassword() {return password;}
    @Override
    public String getUsername() {return phone;}
    @Override
    public boolean isAccountNonExpired() {return true;}
    @Override
    public boolean isAccountNonLocked() {return true;}
    @Override
    public boolean isCredentialsNonExpired() {return true;}
    @Override
    public boolean isEnabled() {return true;}
}