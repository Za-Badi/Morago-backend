package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Category;
import com.habsida.morago.model.entity.File;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.inputs.CreateThemeInput;
import com.habsida.morago.model.inputs.UpdateThemeInput;
import com.habsida.morago.repository.CategoryRepository;
import com.habsida.morago.repository.FileRepository;
import com.habsida.morago.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository repository;
    private final CategoryRepository categoryRepository;
    private final FileRepository fileRepository;
    private final FileService fileService;

    @Transactional(rollbackFor = RuntimeException.class)
    public Theme createTheme(CreateThemeInput input, MultipartFile file) {
        Theme theme = new Theme();
        theme.setCategory(categoryRepository.getReferenceById(input.getCategoryId()));
        theme.setDescription(input.getDescription());
        theme.setName(input.getName());
        theme.setKorean_title(input.getKoreanTitle());
        theme.setPrice(input.getPrice());
        theme.setNightPrice(input.getNightPrice());
        theme.setIsPopular(input.getIsPopular());
        theme.setIsActive(input.getIsActive());
        if (file != null) {
            File icon = fileService.uploadFile(file);
            theme.setIconId(icon);
        } else {
            theme.setIconId(null);
        }
        return repository.save(theme);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Theme updateTheme(UpdateThemeInput input) {
        Theme theme = repository.findById(input.getId()).orElseThrow(EntityNotFoundException::new);
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
                .orElseThrow(() -> new EntityNotFoundException("Entity Not Found with ID " + id));
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
        return repository.findThemesByCategoryId(id);
    }

    public Theme getThemeByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Entity Not Found with name " + name));
    }

    public Set<Theme> getThemeByStatus(Boolean status) {
        return repository.findThemeByIsActive(status);
    }

    public Boolean changeThemeStatus(Long themeId) {
        Theme theme = repository.findById(themeId).orElseThrow(EntityNotFoundException::new);
        theme.setIsActive(!theme.getIsActive());
        repository.save(theme);
        return true;
    }
}
