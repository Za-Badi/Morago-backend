package com.habsida.morago.model.inputs;

import com.habsida.morago.model.dto.ConsultantProfileDTO;
import com.habsida.morago.model.dto.TranslatorProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ConsultantPage {
    private List<ConsultantProfileDTO> content;
    private Integer totalPages;
    private Integer totalElements;
    private Integer size;
    private Integer number;
}