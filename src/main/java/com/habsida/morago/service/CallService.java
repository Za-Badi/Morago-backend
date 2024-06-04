package com.habsida.morago.service;

import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface CallService {
    List<Call> getAllCalls();

    public Call getCallsById(Long id);

    public Call createCall(Call call);

    public Call updateCalls(Long id, Call callDetails);

    public void deleteCall(Long id);

}