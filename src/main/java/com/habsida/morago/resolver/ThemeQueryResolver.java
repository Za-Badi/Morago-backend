package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.dto.ThemeDTO;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.serviceImpl.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ThemeQueryResolver implements GraphQLQueryResolver {

    private final ThemeService themeService;

    public PageOutput<ThemeDTO> getAllThemesByPaging(PagingInput input) {
        return themeService.getAllThemes(input);
    }

    public ThemeDTO getThemeById(Long id) {
        return themeService.getThemeById(id);
    }

    public PageOutput<ThemeDTO> getThemesByCategoriesId(PagingInput input, Long id) {
        return themeService.getThemesByCategoryId(input, id);
    }

    public ThemeDTO getThemesByName(String name) {
        return themeService.getThemeByName(name);
    }

    public PageOutput<ThemeDTO> getThemeByIsActiveStatus(PagingInput input) {
        return themeService.getThemeByIsActiveStatus(input);
    }
}
