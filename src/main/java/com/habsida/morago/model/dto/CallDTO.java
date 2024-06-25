package com.habsida.morago.model.dto;

import lombok.Data;
import com.habsida.morago.model.enums.CallStatus;
import java.time.LocalDateTime;

@Data
public class CallDTO {
    private Long id;
    private UserDTO caller;
    private UserDTO recipient;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer duration;
    private CallStatus status;
    private Double sum;
    private Double commission;
    private Boolean translatorHasRated;
    private Boolean userHasRated;
    private FileDTO file;
    private ThemeDTO theme;
}
