package com.habsida.morago.model.dto;

import lombok.Data;
import com.habsida.morago.model.enums.EPlatform;
import java.time.LocalDateTime;

@Data
public class AppVersionDTO {
    private EPlatform platform;
    private String min;
    private String latest;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
