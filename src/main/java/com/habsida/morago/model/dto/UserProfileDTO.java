package com.habsida.morago.model.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserProfileDTO {
    private Long id;
    private LocalDateTime createdAt;
    private Boolean isFreeCallMade;
    private LocalDateTime updatedAt;
    private UserDTO user;
}
