package com.habsida.morago.model.dto;

import lombok.Data;
import java.util.List;
import java.time.LocalDateTime;

@Data
public class RoleDTO {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List users;
}
