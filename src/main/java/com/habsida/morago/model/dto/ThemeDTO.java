package com.habsida.morago.model.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ThemeDTO {
    private Long id;
    private String name;
    private String koreanTitle;
    private BigDecimal price;
    private BigDecimal nightPrice;
    private String description;
    private Boolean isPopular;
    private Boolean isActive;
    private FileDTO icon;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CategoryDTO category;
    private List translatorProfiles;
}
