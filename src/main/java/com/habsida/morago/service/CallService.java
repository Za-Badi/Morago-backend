package com.habsida.morago.service;

import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.inputs.CallInput;
import com.habsida.morago.model.inputs.CallUpdateInput;
import java.util.List;
import java.util.Optional;



public interface CallService {
    List<Call> getAllCalls();
    Optional<Call> getCallById(Long id);
    Call createCall(CallInput callInput) throws Exception;
    Call updateCall(Long id, CallUpdateInput callUpdateInput) throws Exception;
    void deleteCall(Long id) throws Exception;
}