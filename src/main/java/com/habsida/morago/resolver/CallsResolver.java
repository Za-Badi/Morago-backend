package com.habsida.morago.resolver;


import com.habsida.morago.model.entity.Calls;
import com.habsida.morago.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CallsResolver  {

    @Autowired
    private final CallService callsService;

    public Calls createCall(Calls calls) {
        return callsService.createCalls(calls);
    }

    public Calls updateCall(Long id, Calls callDetails) {
        return callsService.updateCalls(id,callDetails);
    }

    public void deleteCall(Long id) {
        callsService.deleteCall(id);
    }


}