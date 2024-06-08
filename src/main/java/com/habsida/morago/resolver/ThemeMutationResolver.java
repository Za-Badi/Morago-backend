package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.inputs.CreateThemeInput;
import com.habsida.morago.model.inputs.UpdateThemeInput;
import com.habsida.morago.serviceImpl.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ThemeMutationResolver {
    private final ThemeService themeService;

    @MutationMapping
    public Theme createTheme(@Argument CreateThemeInput input) {
        return themeService.createTheme(input);
    }
        @MutationMapping
    public Theme updateThemeById(@Argument UpdateThemeInput input) {
        return themeService.updateTheme(input);
    }
    @MutationMapping
    public Boolean deleteThemeById(@Argument Long id) {
        return themeService.removeThemeById(id);
    }
        @QueryMapping
    public Set<Theme> getAllThemes() {
        return themeService.getAllThemes();
    }

    @QueryMapping
    public Theme getThemeById(@Argument Long id){
        return themeService.getThemeById(id);
    }

    @QueryMapping
    public Set<Theme> getThemesByCategoriesId(@Argument Long id) {
        return themeService.getThemesByCategoryId(id);
    }
    @QueryMapping
    public Theme getThemesByName(@Argument String name) {
        return themeService.getThemeByName(name);
    }

}
