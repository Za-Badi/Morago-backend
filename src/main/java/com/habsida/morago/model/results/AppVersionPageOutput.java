package com.habsida.morago.model.results;

import com.habsida.morago.model.dto.AppVersionDTO;

import java.util.List;

public class AppVersionPageOutput extends PageOutput<AppVersionDTO> {
    public AppVersionPageOutput(int currentPage, int totalPages, long totalElements, List<AppVersionDTO> objectList) {
        super(currentPage, totalPages, totalElements, objectList);
    }
}
