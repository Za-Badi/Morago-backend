package com.habsida.morago.model.results;

import com.habsida.morago.model.entity.Call;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CallPage {
    private List<Call> content;
    private PageInfo pageInfo;

    public CallPage(List<Call> content, PageInfo pageInfo) {
        this.content = content;
        this.pageInfo = pageInfo;
    }
}
