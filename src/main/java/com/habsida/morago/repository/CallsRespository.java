package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Calls;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallsRespository extends JpaRepository<Calls, Long> {
}
