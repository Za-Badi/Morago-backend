package com.habsida.morago.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class UserDTO {
    private Long id;
    private String phone;
    private String password;
    private String firstName;
    private String lastName;
    private Double balance;
    private String fcmToken;
    private String apnToken;
    private Double ratings;
    private Integer totalRatings;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;
    private Boolean isDebtor;
    private Integer onBoardingStatus;
    private FileDTO image;
    private TranslatorProfileDTO translatorProfile;
    private UserProfileDTO userProfile;
    private List <RoleDTO> roles;
    private List deposits;
    private List debtors;
    private List withdrawals;
    private List notifications;
    private List givenRatings;
    private List receivedRatings;
}
