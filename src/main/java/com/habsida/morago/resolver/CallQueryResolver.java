package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.dto.CallDTO;
import com.habsida.morago.model.enums.CallStatus;
import com.habsida.morago.model.results.CallPage;
import com.habsida.morago.model.results.PageInfo;
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

    public List<CallDTO> getAllCalls() {
        return callService.getAllCalls();
    }

    public CallDTO getCallById(Long id) throws Exception {
        return callService.getCallById(id);
    }

    public List<CallDTO> getAllFreeCalls() {
        return callService.getAllFreeCall();
    }

    public CallPage getMissedCalls(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CallDTO> callPage = callService.getAllMissedCalls(userId, pageable);

        PageInfo pageInfo = new PageInfo(
                callPage.getTotalPages(),
                callPage.getTotalElements(),
                callPage.getNumber(),
                callPage.getSize()
        );

        return new CallPage(callPage.getContent(), pageInfo);
    }

    public CallPage getCallsByOutgoingIncomingStatus(CallStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CallDTO> callPage = callService.getCallsByOutgoingIncomingStatus(status, pageable);

        PageInfo pageInfo = new PageInfo(
                callPage.getTotalPages(),
                callPage.getTotalElements(),
                callPage.getNumber(),
                callPage.getSize()
        );
        return new CallPage(callPage.getContent(), pageInfo);
    }

    public CallPage getCallsByOutgoingIncoming(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CallDTO> callPage = callService.getCallsByOutgoingIncoming(pageable);

        PageInfo pageInfo = new PageInfo(
                callPage.getTotalPages(),
                callPage.getTotalElements(),
                callPage.getNumber(),
                callPage.getSize()
        );
        return new CallPage(callPage.getContent(), pageInfo);
    }

    public List<CallDTO> getCallsByUserId(Long userId) {
        return callService.getCallsByUserId(userId);
    }
}
