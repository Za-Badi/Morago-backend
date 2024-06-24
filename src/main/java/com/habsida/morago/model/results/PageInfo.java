package com.habsida.morago.model.results;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PageInfo {
    private int totalPages;
    private long totalElements;
    private int currentPage;
    private int pageSize;

    public PageInfo(int totalPages, long totalElements, int currentPage, int pageSize) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
}
