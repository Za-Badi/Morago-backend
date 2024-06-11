package com.habsida.morago.resolver;

import com.habsida.morago.exceptions.GlobalException;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.inputs.CreateThemeInput;
import com.habsida.morago.model.inputs.UpdateThemeInput;
import com.habsida.morago.serviceImpl.ThemeService;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import jakarta.servlet.http.Part;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ThemeMutationResolver {
    private final ThemeService themeService;

    @MutationMapping
    public Theme createTheme(@Argument CreateThemeInput input, DataFetchingEnvironment env) {
        MultipartFile mFile = null;
        try {
            LinkedHashMap<String, Object> envInput = env.getArgument("input");
            Part part = (Part) envInput.get("icon");
            if (part == null) {
                return themeService.createTheme(input, mFile);
            }
            mFile = new MockMultipartFile(part.getSubmittedFileName(), part.getSubmittedFileName(), part.getContentType(), part.getInputStream());
        } catch (IOException e) {
            throw new GlobalException("Error");
        }
        return themeService.createTheme(input, mFile);
    }

    @MutationMapping
    public Theme updateThemeById(@Argument UpdateThemeInput input) {
        return themeService.updateTheme(input);
    }

    @MutationMapping
    public Boolean deleteThemeById(@Argument Long id) {
        return themeService.removeThemeById(id);
    }

    @MutationMapping
    public Boolean changeThemeStatus(@Argument Long id) {
        return themeService.changeThemeStatus(id);
    }

    @MutationMapping
    public Boolean changeThemePopularity(@Argument Long id) {
        return themeService.changeThemePopularity(id);
    }

    @QueryMapping
    public Set<Theme> getAllThemes() {
        return themeService.getAllThemes();
    }

    @QueryMapping
    public Theme getThemeById(@Argument Long id) {
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

    @QueryMapping
    public Set<Theme> getThemeByStatus(@Argument Boolean status) {
        return themeService.getThemeByStatus(status);
    }
}
