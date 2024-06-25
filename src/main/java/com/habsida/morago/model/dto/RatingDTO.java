package com.habsida.morago.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RatingDTO {
    private Long id;
    private UserDTO whoUser;
    private UserDTO toWhomUser;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Double ratings;
    private FileDTO file;
}
