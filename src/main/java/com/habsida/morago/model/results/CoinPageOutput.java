package com.habsida.morago.model.results;

import com.habsida.morago.model.dto.CoinDTO;

import java.util.List;

public class CoinPageOutput extends PageOutput<CoinDTO> {
    public CoinPageOutput(int currentPage, int totalPages, long totalElements, List<CoinDTO> objectList) {
        super(currentPage, totalPages, totalElements, objectList);
    }
}
