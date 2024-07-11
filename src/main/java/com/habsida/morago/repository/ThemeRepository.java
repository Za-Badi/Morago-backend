package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Theme;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
    @EntityGraph(value = "themes.field")
    @Query("SELECT t FROM Theme t WHERE t.name = :name")
    Optional<Theme> findByName(String name);

    @NotNull
    @EntityGraph(value = "themes.field")
    Optional<Theme> findById(@NotNull Long id);

    @EntityGraph(value = "themes.field")
    @Query(value = "SELECT th FROM Theme th WHERE th.category.id = :id")
    Page<Theme> findThemesByCategoryId(Pageable pageable, Long id);

    @EntityGraph(value = "themes.field")
    @Query(value = "SELECT th FROM Theme th  WHERE th.isActive = true")
    Page<Theme> findThemeByIsActive(Pageable pageable);

    @NotNull
    @EntityGraph(value = "themes.field")
    Page<Theme> findAll(Pageable pageable);




}
