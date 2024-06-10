package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    @Query(value = "select * from themes WHERE name = :name", nativeQuery = true)
    Optional<Theme> findByName(String name);

    @Query(value = "SELECT * FROM themes WHERE categories_id = :id", nativeQuery = true)
    Set<Theme> findThemesByCategoryId(Long id);

    Set<Theme> findThemeByIsActive(Boolean isActive);


}
