package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Theme;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

//    @NamedEntityGraph(name = "theme.field",
//            attributeNodes = {@NamedAttributeNode("category")})
//    @EntityGraph(value = "theme.field")

//    @EntityGraph("themes.field") ;
    @Query(value = "select * from themes WHERE name = :name", nativeQuery = true)
    Optional<Theme> findByName(String name);

    @Query(value = "SELECT * FROM themes WHERE categories_id = :id", nativeQuery = true)
    Optional<Set<Theme>> findThemesByCategoryId(Long id);
}
