package com.habsida.morago.resolver;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.serviceImpl.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ThemeQueryResolver implements GraphQLQueryResolver {

    private final ThemeService themeService;

    public Set<Theme> getAllThemes() {
        return themeService.getAllThemes();
    }

    public Theme getThemeById(Long id) {
        return themeService.getThemeById(id);
    }

    public Set<Theme> getThemesByCategoriesId(Long id) {
        return themeService.getThemesByCategoryId(id);
    }

    public Theme getThemesByName(String name) {
        return themeService.getThemeByName(name);
    }

    public Set<Theme> getThemeByStatus(Boolean status) {
        return themeService.getThemeByStatus(status);
    }

}
