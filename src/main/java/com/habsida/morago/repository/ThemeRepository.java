package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Theme;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
    @EntityGraph(value = "themes.field")
    @Query(value = "select * from themes WHERE name = :name", nativeQuery = true)
    Optional<Theme> findByName(String name);
    @NotNull
    @EntityGraph(value = "themes.field")
    Optional<Theme> findById(@NotNull Long id);
    @EntityGraph(value = "themes.field")
    @Query(value = "SELECT * FROM themes WHERE categories_id = :id", nativeQuery = true)
    Set<Theme> findThemesByCategoryId(Long id);

    @EntityGraph(value = "themes.field")
    Set<Theme> findThemeByIsActive(Boolean isActive);


}
