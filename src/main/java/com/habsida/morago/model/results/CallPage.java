package com.habsida.morago.model.results;

import com.habsida.morago.model.dto.CallDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CallPage {
    private List<CallDTO> content;
    private PageInfo pageInfo;

    public CallPage(List<CallDTO> content, PageInfo pageInfo) {
        this.content = content;
        this.pageInfo = pageInfo;
    }
}
