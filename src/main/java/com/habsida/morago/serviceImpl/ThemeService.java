package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Category;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.inputs.CreateThemeInput;
import com.habsida.morago.model.inputs.UpdateThemeInput;
import com.habsida.morago.repository.CategoryRepository;
import com.habsida.morago.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

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
        theme.setKorean_title(input.getKorean_title());
        theme.setPrice(input.getPrice());
        theme.setNightPrice(input.getNightPrice());
        theme.setIsPopular(input.getIsPopular());
        theme.setIsActive(input.getIsActive());
        return repository.save(theme);
    }

    public Theme updateTheme(UpdateThemeInput input) {
        Theme theme = new Theme();
        System.out.println("category "+input.getCategoryId());
        if (input.getCategoryId() != null ) {
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
        if (input.getIsPopular() != null ) {
            theme.setIsPopular(input.getIsPopular());
        }
        if (input.getIsActive() != null ) {
            theme.setIsActive(input.getIsActive());
        }
        return repository.save(theme);
    }
    public Theme getThemeById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Boolean removeThemeById(Long id) {
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
            return true;
        }
        throw new EntityNotFoundException();


    }
    public List<Theme> getAllThemes(){
        return repository.findAll();

    }

}
