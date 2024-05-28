package com.habsida.morago.servieImpl;

import com.habsida.morago.model.entity.Calls;

import com.habsida.morago.repository.CallsRespository;
import com.habsida.morago.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CallServiceImpl implements CallService {

    @Autowired
    private CallsRespository callsRespository;

    @Override
    public List<Calls> getAllCalls() {
        return callsRespository.findAll();
    }

    @Override

    public Optional<Calls> getCallsById(Long id) {
        return callsRespository.findById(id);
    }

    @Override
    public Calls createCalls(Calls calls) {
        return callsRespository.save(calls);
    }

    @Override
    public Calls updateCalls(Long id, Calls callsDetails) {
        Optional<Calls> optionalCalls = callsRespository.findById(id);
        if(optionalCalls.isPresent()) {
            Calls calls = optionalCalls.get();
            calls.setCaller(callsDetails.getCaller());
            calls.setRecipient(callsDetails.getRecipient());
            calls.setCreatedAt(callsDetails.getCreatedAt());
            calls.setDuration(callsDetails.getDuration());
            calls.setStatus(callsDetails.getStatus());
            calls.setSum(callsDetails.getSum());
            calls.setCommission((callsDetails.getCommission()));
            calls.setTranslatorHasRated(callsDetails.getTranslatorHasRated());
            calls.setUserHasRated(callsDetails.getUserHasRated());
            calls.setFile(callsDetails.getFile());
            calls.setTheme(callsDetails.getTheme());
        }
        return null;
    }

    @Override
    public void deleteCall(Long id) {
        callsRespository.deleteById(id);
    }

}
