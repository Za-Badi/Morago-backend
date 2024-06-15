//package com.habsida.morago.resolver;
//
//
//import com.coxautodev.graphql.tools.GraphQLMutationResolver;
//import com.habsida.morago.model.entity.Theme;
//import com.habsida.morago.model.inputs.CreateThemeInput;
//import com.habsida.morago.model.inputs.UpdateThemeInput;
//import com.habsida.morago.serviceImpl.ThemeService;
//import graphql.GraphQLException;
//import graphql.schema.DataFetchingEnvironment;
//import lombok.RequiredArgsConstructor;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.Part;
//import java.io.IOException;
//import java.util.LinkedHashMap;
//import java.util.Set;
//
//@Controller
//@RequiredArgsConstructor
//public class ThemeMutationResolver implements GraphQLMutationResolver {
//    private final ThemeService themeService;
//
//
//    public Theme createTheme(CreateThemeInput input, DataFetchingEnvironment env) throws IOException {
//        MultipartFile mFile = null;
//        try {
//            LinkedHashMap<String, Object> envInput = env.getArgument("input");
//            Part part = (Part) envInput.get("icon");
//            if (part == null) {
//                return themeService.createTheme(input, mFile);
//            }
//            mFile = new MockMultipartFile(part.getSubmittedFileName(), part.getSubmittedFileName(), part.getContentType(), part.getInputStream());
//        } catch (IOException e) {
//            throw new GraphQLException("Error");
//        }
//        return themeService.createTheme(input, mFile);
//    }
//
//    public Theme updateThemeById(UpdateThemeInput input) {
//        return themeService.updateTheme(input);
//    }
//
//
//    public Boolean deleteThemeById(Long id) {
//        return themeService.removeThemeById(id);
//    }
//
//
//    public Boolean changeThemeStatus(Long id) {
//        return themeService.changeThemeStatus(id);
//    }
//
//    public Boolean changeThemePopularity(Long id) {
//        return themeService.changeThemePopularity(id);
//    }
//}
