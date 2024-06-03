package com.habsida.morago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.habsida.morago.model.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}

