package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FilesRepository extends JpaRepository<Files, Long> {
}
