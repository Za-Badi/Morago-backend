package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.exceptions.CallStatusNotFoundException;
import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.enums.CallStatus;
import com.habsida.morago.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CallQueryResolver implements GraphQLQueryResolver {
    private final CallService callService;

    public List<Call> getAllCalls() {
        return callService.getAllCalls();
    }

    public Call getCallById(Long id) throws Exception {
        return callService.getCallById(id);
    }

//    public List<Call> getCallsByStatus(CallStatus status) {
//        return callService.getCallByStatus(status);
//    }

    public List<Call> getAllMissedCalls( CallStatus callStatus) throws Exception{
        return callService.getAllMissedCalls(callStatus);
    }

    public List<Call> getCallsByUserId(Long userId) {
        return callService.getCallsByUserId(userId);
    }

    public List<Call> getCallsByOutgoingIncoming() {
        return callService.getCallsByOutgoingIncoming();
    }

    public List<Call> getCallsByOutgoingIncomingStatus(CallStatus callStatus) {
        if (callStatus == CallStatus.INCOMING_CALL || callStatus == CallStatus.OUTGOING_CALL) {
            return callService.getCallsByOutgoingIncomingStatus(callStatus);
        } else {
            throw new CallStatusNotFoundException(callStatus);
        }
    }
}
