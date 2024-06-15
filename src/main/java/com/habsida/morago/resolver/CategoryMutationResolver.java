package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.model.entity.Category;
import com.habsida.morago.model.inputs.CreateCategoryInput;
import com.habsida.morago.model.inputs.UpdateCategoryInput;
import com.habsida.morago.serviceImpl.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class CategoryMutationResolver implements GraphQLMutationResolver {
    private final CategoryService categoryService;


    public Category createCategory(CreateCategoryInput input) {
        return categoryService.createCategory(input);
    }

    public Category updateCategory(UpdateCategoryInput input) {
        return categoryService.updateCategory(input);
    }

    public Boolean deleteCategoryById(Long id) {
        return categoryService.deleteCategoryById(id);
    }

    public Boolean changeCategoryStatus(Long id) {
        return categoryService.changeCategoryStatus(id);
    }

}
