package com.habsida.morago.model.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class LanguageDTO {
    private Long id;
    private LocalDateTime createdAt;
    private String name;
    private LocalDateTime updatedAt;
    private List <TranslatorProfileDTO>  translatorProfiles;
}
