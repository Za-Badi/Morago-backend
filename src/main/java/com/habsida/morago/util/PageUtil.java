package com.habsida.morago.util;

import com.habsida.morago.model.enums.ESort;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.PageOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;

public class PageUtil {
    public static Pageable buildPageable(PagingInput input) {
        Pageable paging;
        if (input.getSort().equals(ESort.DES)) {
            paging = PageRequest.of(input.getPageNo(), input.getPageSize(), Sort.by(input.getSortBy()).ascending());
        } else {
            paging = PageRequest.of(input.getPageNo(), input.getPageSize(), Sort.by(input.getSortBy()).descending());
        }
        return paging;
    }
    public static Pageable buildPageable(PagingInput input, Sort sort) {
        Pageable paging;
        if (input.getSort().equals(ESort.DES)) {
            paging = PageRequest.of(input.getPageNo(), input.getPageSize(), sort);
        } else {
            paging = PageRequest.of(input.getPageNo(), input.getPageSize(), sort);
        }
        return paging;
    }
    public static  <T> PageOutput<T> buildPage(Page<T> page) {
        if (!page.hasContent()) {
            return new PageOutput<T>(0, 0, 0, new ArrayList<>());
        }
        PageOutput<T> pageOutput = new PageOutput<T>();
        pageOutput.setObjectList(page.toList());
        pageOutput.setCurrentPage(page.getNumber());
        pageOutput.setTotalPages(page.getTotalPages());
        pageOutput.setTotalElements(page.getTotalElements());
        return pageOutput;
    }
}
