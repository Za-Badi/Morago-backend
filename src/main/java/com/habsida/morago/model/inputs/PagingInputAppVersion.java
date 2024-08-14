package com.habsida.morago.model.inputs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.habsida.morago.model.enums.ESort;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(onConstructor = @__(@JsonCreator))
public class PagingInputAppVersion {
    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private String sortBy = "platform"; // Default sort by platform
    private ESort sort = ESort.DES;

    @JsonCreator
    public PagingInputAppVersion(
            @JsonProperty("pageNo") Integer pageNo,
            @JsonProperty("pageSize") Integer pageSize,
            @JsonProperty("sortBy") String sortBy,
            @JsonProperty("sort") ESort sort) {

        this.pageNo = pageNo != null ? pageNo : this.pageNo;
        setPageSize(pageSize != null ? pageSize : this.pageSize); // Use setter to enforce max size
        this.sortBy = sortBy != null ? sortBy : this.sortBy;
        this.sort = sort != null ? sort : this.sort;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != null && pageSize > 100) {
            throw new IllegalArgumentException("Page size cannot be greater than 100");
        }
        this.pageSize = pageSize;
    }
}
