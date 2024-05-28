package com.habsida.morago.service;

import com.habsida.morago.model.entity.Calls;

import java.util.List;
import java.util.Optional;

public interface CallService {
    List<Calls> getAllCalls();

    public Optional<Calls> getCallsById(Long id);

   public Calls createCalls(Calls calls);

    public Calls updateCalls(Long id, Calls callDetails);

    public void deleteCall(Long id);

}
