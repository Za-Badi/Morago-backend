package com.habsida.morago.model.dto;

import lombok.Data;
import com.habsida.morago.model.enums.PaymentStatus;
import java.time.LocalDateTime;

@Data
public class WithdrawalsDTO {
    private Long id;
    private String accountNumber;
    private String accountHolder;
    private Float sum;
    private PaymentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDTO user;
}
