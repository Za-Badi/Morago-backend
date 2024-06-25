package com.habsida.morago.model.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CoinDTO {
    private Long id;
    private Double coin;
    private Double won;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
