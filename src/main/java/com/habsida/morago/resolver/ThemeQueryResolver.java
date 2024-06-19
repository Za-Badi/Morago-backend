package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.serviceImpl.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ThemeQueryResolver implements GraphQLQueryResolver {

    private final ThemeService themeService;

    public PageOutput<Theme> getAllThemesByPaging(PagingInput input) {
        return themeService.getAllThemes(input);
    }

    public Theme getThemeById(Long id) {
        return themeService.getThemeById(id);
    }

    public PageOutput<Theme> getThemesByCategoriesId(PagingInput input, Long id) {
        return themeService.getThemesByCategoryId(input, id);
    }

    public Theme getThemesByName(String name) {
        return themeService.getThemeByName(name);
    }

    public PageOutput<Theme> getThemeByIsActiveStatus(PagingInput input) {
        return themeService.getThemeByIsActiveStatus(input);

    }

}
