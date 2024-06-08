package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.inputs.CreateThemeInput;
import com.habsida.morago.model.inputs.UpdateThemeInput;
import com.habsida.morago.repository.CategoryRepository;
import com.habsida.morago.repository.ThemeRepository;
import graphql.GraphQLException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository repository;
    private final CategoryRepository categoryRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public Theme createTheme(CreateThemeInput input) {
        Theme theme = new Theme();
        theme.setCategory(categoryRepository.getReferenceById(input.getCategoryId()));
        theme.setDescription(input.getDescription());
        theme.setName(input.getName());
        theme.setKorean_title(input.getKoreanTitle());
        theme.setPrice(input.getPrice());
        theme.setNightPrice(input.getNightPrice());
        theme.setIsPopular(input.getIsPopular());
        theme.setIsActive(input.getIsActive());
        return repository.save(theme);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Theme updateTheme(UpdateThemeInput input) {
        Theme theme = new Theme();
        if (input.getCategoryId() != null && input.getCategoryId() != 0) {
            theme.setCategory(categoryRepository.getReferenceById(input.getCategoryId()));
        }
        if (input.getDescription() != null && !input.getDescription().isEmpty()) {
            theme.setDescription(input.getDescription());
        }
        if (input.getName() != null && !input.getName().isEmpty()) {
            theme.setName(input.getName());
        }
        if (input.getKorean_title() != null && !input.getKorean_title().isEmpty()) {
            theme.setKorean_title(input.getKorean_title());
        }
        if (input.getPrice() != null) {
            theme.setPrice(input.getPrice());
        }
        if (input.getNightPrice() != null) {
            theme.setNightPrice(input.getNightPrice());
        }
        if (input.getIsPopular() != null) {
            theme.setIsPopular(input.getIsPopular());
        }
        if (input.getIsActive() != null) {
            theme.setIsActive(input.getIsActive());
        }
        return repository.save(theme);
    }

    public Theme getThemeById(Long id) {
        return repository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Entity Not Found with ID "+id));
    }

    public Boolean removeThemeById(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        throw new EntityNotFoundException();


    }

    public Set<Theme> getAllThemes() {
        return new HashSet<>(repository.findAll());
    }

    public Set<Theme> getThemesByCategoryId(Long id) {
        return repository.findThemesByCategoryId(id).orElseThrow(GraphQLException::new);
    }

    public Theme getThemeByName(String name) {
        return repository.findByName(name).orElseThrow(EntityNotFoundException::new);
    }


}
