package com.habsida.morago.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
public class FileDTO {
    private Long id;
    private String path;
    private String type;
    private String originalTitle;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
//    private UserDTO user;
}

