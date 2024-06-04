package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Call;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallRespository extends JpaRepository<Call, Long> {
}