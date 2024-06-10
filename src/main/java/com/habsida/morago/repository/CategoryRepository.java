package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    Set<Category> findByIsActive(Boolean status);

    Boolean existsByName(String name);
}
