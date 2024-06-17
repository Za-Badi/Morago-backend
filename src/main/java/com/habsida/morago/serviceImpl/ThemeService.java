package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.entity.Category;
import com.habsida.morago.model.entity.File;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.inputs.CreateThemeInput;
import com.habsida.morago.model.inputs.UpdateThemeInput;
import com.habsida.morago.repository.CategoryRepository;
import com.habsida.morago.repository.ThemeRepository;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository repository;
    private final CategoryRepository categoryRepository;
    private final FileService fileService;

    @Transactional(rollbackFor = RuntimeException.class)
    public Theme createTheme(CreateThemeInput input, MultipartFile icon) {
        Theme theme = new Theme();

        if (input.getCategoryId() != null && input.getCategoryId() != 0) {
            theme.setCategory(categoryRepository.findById(input.getCategoryId()).orElseThrow(() -> new EntityNotFoundException("No Category with id: " + input.getCategoryId())));
        }
        if (input.getDescription() != null && !input.getDescription().isEmpty()) {
            theme.setDescription(input.getDescription());
        } else {
            throw new GraphqlException("Description value is not valid");
        }
        if (input.getName() != null && !input.getName().isEmpty()) {
            theme.setName(input.getName());
        } else {
            throw new GraphqlException("Name value is not valid");
        }
        if (input.getKoreanTitle() != null && !input.getKoreanTitle().isEmpty()) {
            theme.setKoreanTitle(input.getKoreanTitle());
        } else {
            throw new GraphqlException("KoreanTitle value is not valid");
        }
        if (!input.getPrice().equals(BigDecimal.ZERO)) {
            theme.setPrice(input.getPrice());
        } else {
            throw new GraphqlException("Price value is not valid");
        }
        if (!input.getNightPrice().equals(BigDecimal.ZERO)) {
            theme.setNightPrice(input.getNightPrice());
        } else {
            throw new GraphqlException("nightPrice value is not valid");
        }
        theme.setIsPopular(input.getIsPopular());
        theme.setIsActive(input.getIsActive());
        if (icon != null) {
            try {
                File file = fileService.uploadFile(icon);
                theme.setIcon(file);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            theme.setIcon(null);
        }
        return repository.save(theme);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Theme updateTheme(UpdateThemeInput input,  MultipartFile icon) {
        Theme theme = repository.findById(input.getId()).orElseThrow(() -> new EntityNotFoundException("No Theme with id: " + input.getId()));
        if (input.getCategoryId() != null && input.getCategoryId() != 0) {
            theme.setCategory(categoryRepository.findById(input.getCategoryId()).orElseThrow(() -> new EntityNotFoundException("No Category with id: " + input.getCategoryId())));
        }
        if (input.getDescription() != null && !input.getDescription().isEmpty()) {
            theme.setDescription(input.getDescription());
        }
        if (input.getName() != null && !input.getName().isEmpty()) {
            theme.setName(input.getName());
        }
        if (input.getKoreanTitle() != null && !input.getKoreanTitle().isEmpty()) {
            theme.setKoreanTitle(input.getKoreanTitle());
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
        if (icon != null) {
          try{
              File file = fileService.uploadFile(icon);
              theme.setIcon(file);
          }
          catch (IOException e) {
              e.printStackTrace();
          }
        }
        return repository.save(theme);
    }

    public Theme getThemeById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Theme Not Found with ID " + id));
    }

    public Boolean removeThemeById(Long id) {
        Theme theme = getThemeById(id);
        repository.delete(theme);
        return true;
    }
    @Transactional
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
        Theme theme = repository.findById(themeId).orElseThrow(() -> new EntityNotFoundException("Theme Not Found with ID " + themeId));
        theme.setIsActive(!theme.getIsActive());
        repository.save(theme);
        return true;
    }

    public Boolean changeThemePopularity(Long themeId, Boolean themePopularity) {
        Theme theme = repository.findById(themeId).orElseThrow(() -> new EntityNotFoundException("Theme Not Found with ID " + themeId));
        theme.setIsActive(themePopularity);
        repository.save(theme);
        return true;
    }
}
