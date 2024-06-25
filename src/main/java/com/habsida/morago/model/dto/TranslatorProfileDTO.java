package com.habsida.morago.model.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

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
    private List languages;
    private List themes;
    private UserDTO user;
}
