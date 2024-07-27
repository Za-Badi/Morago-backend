package com.habsida.morago.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class TranslatorProfileDTO {
    private Long id;
    private LocalDateTime createdAt;
    private String dateOfBirth;
    private String email;
    private Boolean isAvailable;
    private Boolean isOnline;
    private String levelOfKorean;
    private LocalDateTime updatedAt;
    private List <LanguageDTO> languages;
    private List <ThemeDTO> themes;
    private UserDTO user;
}
