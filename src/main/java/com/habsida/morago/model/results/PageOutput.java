package com.habsida.morago.model.results;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageOutput<T> {
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private List<T> objectList;
}
