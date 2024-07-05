package com.habsida.morago.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class UserProfileDTO {
    private Long id;
    private LocalDateTime createdAt;
    private Boolean isFreeCallMade;
    private LocalDateTime updatedAt;
    private UserDTO user;
}
