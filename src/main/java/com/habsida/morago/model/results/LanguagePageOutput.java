package com.habsida.morago.model.results;

import com.habsida.morago.model.dto.LanguageDTO;

import java.util.List;

public class LanguagePageOutput extends PageOutput<LanguageDTO> {
    public LanguagePageOutput(int currentPage, int totalPages, long totalElements, List<LanguageDTO> objectList) {
        super(currentPage, totalPages, totalElements, objectList);
    }
}
