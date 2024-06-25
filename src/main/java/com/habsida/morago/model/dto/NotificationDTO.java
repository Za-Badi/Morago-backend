package com.habsida.morago.model.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class NotificationDTO {
    private Long id;
    private String title;
    private String text;
    private LocalDate date;
    private LocalTime time;
    private UserDTO user;
}
