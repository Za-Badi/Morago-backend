package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    @Query(value = "SELECT th FROM Category th  WHERE th.isActive = true")
    Page<Category> findByIsActive(Pageable pageable);

    Boolean existsByName(String name);

    @NotNull
    @EntityGraph(value = "themes.field")
    Page<Category> findAll(Pageable pageable);

}
