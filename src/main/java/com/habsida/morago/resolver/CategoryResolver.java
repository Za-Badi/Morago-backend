package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.Category;
import com.habsida.morago.model.inputs.CreateCategoryInput;
import com.habsida.morago.model.inputs.UpdateCategoryInput;
import com.habsida.morago.serviceImpl.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
@RequiredArgsConstructor
public class CategoryResolver {
    private final CategoryService categoryService;


    @MutationMapping
    public Category createCategory(@Argument CreateCategoryInput input) {
        return categoryService.createCategory(input);
    }

    @MutationMapping
    public Category updateCategory(@Argument UpdateCategoryInput input) {
        return categoryService.updateCategory(input);
    }

    @MutationMapping
    public Boolean deleteCategoryById(@Argument Long id) {
        return categoryService.deleteCategoryById(id);
    }

    @QueryMapping
    public Set<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @QueryMapping
    public Category getCategoryById(@Argument Long id) {
        return categoryService.getCategoryById(id);
    }

    @QueryMapping
    public Category getCategoryByName(@Argument String name) {
        return categoryService.getCategoryByName(name);
    }

    @QueryMapping
    public Set<Category> getCategoryByStatus(@Argument Boolean status) {
        return categoryService.getCategoryByStatus(status);
    }
}
