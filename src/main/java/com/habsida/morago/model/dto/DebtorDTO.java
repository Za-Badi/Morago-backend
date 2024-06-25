package com.habsida.morago.model.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DebtorDTO {
    private Long id;
    private String accountHolder;
    private String nameOfBank;
    private Boolean isPaid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDTO user;
}
