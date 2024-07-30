package com.habsida.morago.model.results;

import com.habsida.morago.model.dto.FrequentlyAskedQuestionDTO;

import java.util.List;

public class FrequentlyAskedQuestionPageOutput extends PageOutput<FrequentlyAskedQuestionDTO> {
    public FrequentlyAskedQuestionPageOutput(int currentPage, int totalPages, long totalElements, List<FrequentlyAskedQuestionDTO> objectList) {
        super(currentPage, totalPages, totalElements, objectList);
    }
}
