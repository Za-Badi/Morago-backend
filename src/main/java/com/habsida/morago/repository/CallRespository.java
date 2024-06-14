package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.enums.CallStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CallRespository extends JpaRepository<Call, Long> {
    List<Call> findByStatus(CallStatus callStatus);

    List<Call> findByCallerId(Long id);
}