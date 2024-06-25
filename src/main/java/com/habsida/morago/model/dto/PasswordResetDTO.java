package com.habsida.morago.model.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PasswordResetDTO {
    private Long id;
    private String phone;
    private String token;
    private Integer resetCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
