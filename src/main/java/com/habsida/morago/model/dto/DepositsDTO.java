package com.habsida.morago.model.dto;

import lombok.Data;
import com.habsida.morago.model.enums.PaymentStatus;
import java.time.LocalDateTime;

@Data
public class DepositsDTO {
    private Long id;
    private String accountHolder;
    private String nameOfBank;
    private Double coin;
    private Double won;
    private PaymentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDTO user;
}
