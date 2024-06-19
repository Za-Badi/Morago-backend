package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.entity.File;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.inputs.CreateThemeInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.inputs.UpdateThemeInput;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.repository.CategoryRepository;
import com.habsida.morago.repository.ThemeRepository;
import com.habsida.morago.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

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
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            theme.setIcon(null);
        }
        return repository.save(theme);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Theme updateTheme(UpdateThemeInput input, MultipartFile icon) {
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
            try {
                File file = fileService.uploadFile(icon);
                theme.setIcon(file);
            } catch (IOException e) {
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
        File icon = theme.getIcon();
        repository.delete(theme);
        fileService.deleteById(icon.getId());
        return true;
    }

    @Transactional
    public PageOutput<Theme> getAllThemes(PagingInput input) {
        Page<Theme> pageRequest = repository.findAll(PageUtil.buildPageable(input));
        return PageUtil.buildPage(pageRequest);
    }

    public PageOutput<Theme> getThemesByCategoryId(PagingInput input, Long id) {
        Page<Theme> pagedrequest = repository.findThemesByCategoryId(PageUtil.buildPageable(input), id);
        return PageUtil.buildPage(pagedrequest);
    }

    public Theme getThemeByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Entity Not Found with name " + name));
    }

    public PageOutput<Theme> getThemeByIsActiveStatus(PagingInput input) {
        Page<Theme> pagedRequest = repository.findThemeByIsActive(PageUtil.buildPageable(input));
        return PageUtil.buildPage(pagedRequest);
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
