package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.dto.FileDTO;
import com.habsida.morago.model.dto.ThemeDTO;
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
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    public ThemeDTO createTheme(CreateThemeInput input, MultipartFile icon) {
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
            throw new GraphqlException("NightPrice value is not valid");
        }
        theme.setIsPopular(input.getIsPopular());
        theme.setIsActive(input.getIsActive());
        if (icon != null) {
            try {
                FileDTO fileDTO = fileService.uploadFile(icon);
                theme.setIcon(modelMapper.map(fileDTO, File.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            theme.setIcon(null);
        }
        Theme savedTheme = repository.save(theme);
        return modelMapper.map(savedTheme, ThemeDTO.class);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public ThemeDTO updateTheme(UpdateThemeInput input, MultipartFile icon) {
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
                FileDTO fileDTO = fileService.uploadFile(icon);
                theme.setIcon(modelMapper.map(fileDTO, File.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Theme updatedTheme = repository.save(theme);
        return modelMapper.map(updatedTheme, ThemeDTO.class);
    }

    public ThemeDTO getThemeById(Long id) {
        Theme theme = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Theme Not Found with ID " + id));
        return modelMapper.map(theme, ThemeDTO.class);
    }

    public Boolean removeThemeById(Long id) {
        Theme theme = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Theme Not Found with ID " + id));
        File icon = theme.getIcon();
        repository.delete(theme);
        if (icon != null) {
            fileService.deleteById(icon.getId());
        }
        return true;
    }

    @Transactional
    public PageOutput<ThemeDTO> getAllThemes(PagingInput input) {
        Page<Theme> pageRequest = repository.findAll(PageUtil.buildPageable(input));
        return PageUtil.buildPage(pageRequest.map(theme -> modelMapper.map(theme, ThemeDTO.class)));
    }

    @Transactional
    public PageOutput<ThemeDTO> getThemesByCategoryId(PagingInput input, Long id) {
        Page<Theme> pagedRequest = repository.findThemesByCategoryId(PageUtil.buildPageable(input), id);
        return PageUtil.buildPage(pagedRequest.map(theme -> modelMapper.map(theme, ThemeDTO.class)));
    }

    public ThemeDTO getThemeByName(String name) {
        Theme theme = repository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Entity Not Found with name " + name));
        return modelMapper.map(theme, ThemeDTO.class);
    }

    @Transactional
    public PageOutput<ThemeDTO> getThemeByIsActiveStatus(PagingInput input) {
        Page<Theme> pagedRequest = repository.findThemeByIsActive(PageUtil.buildPageable(input));
        return PageUtil.buildPage(pagedRequest.map(theme -> modelMapper.map(theme, ThemeDTO.class)));
    }

    public Boolean changeThemeStatus(Long themeId) {
        Theme theme = repository.findById(themeId).orElseThrow(() -> new EntityNotFoundException("Theme Not Found with ID " + themeId));
        theme.setIsActive(!theme.getIsActive());
        repository.save(theme);
        return true;
    }

    public Boolean changeThemePopularity(Long themeId, Boolean themePopularity) {
        Theme theme = repository.findById(themeId).orElseThrow(() -> new EntityNotFoundException("Theme Not Found with ID " + themeId));
        theme.setIsPopular(themePopularity);
        repository.save(theme);
        return true;
    }
}
