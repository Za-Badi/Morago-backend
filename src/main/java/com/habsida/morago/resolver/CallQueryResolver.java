package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.dto.CallDTO;
import com.habsida.morago.model.enums.CallStatus;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.CallPage;
import com.habsida.morago.model.results.PageInfo;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CallQueryResolver implements GraphQLQueryResolver {
    private final CallService callService;

    public PageOutput<CallDTO> getAllCalls(PagingInput pagingInput) {
        return callService.getAllCalls(pagingInput);
    }

    public CallDTO getCallById(Long id) throws Exception {
        return callService.getCallById(id);
    }

    public PageOutput<CallDTO> getAllFreeCalls(PagingInput pagingInput) {
        return callService.getAllFreeCall(pagingInput);
    }

    public PageOutput<CallDTO> getMissedCalls(Long userId, PagingInput pagingInput) {
        return callService.getAllMissedCalls(userId, pagingInput);
    }

    public PageOutput<CallDTO> getCallsByOutgoingIncomingStatus(CallStatus status, PagingInput pagingInput) {
        return callService.getCallsByOutgoingIncomingStatus(status, pagingInput);

    }

    public PageOutput<CallDTO> getCallsByOutgoingIncoming(PagingInput pagingInput) {
        return callService.getCallsByOutgoingIncoming(pagingInput);
    }

    public PageOutput<CallDTO> getCallsByUserId(Long userId, PagingInput pagingInput) {
        return callService.getCallsByUserId(userId, pagingInput);
    }
}
