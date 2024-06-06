package com.habsida.morago.repository;

import com.habsida.morago.model.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FilesRepository extends JpaRepository<File, Long> {
}
