package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.Category;
import com.habsida.morago.model.inputs.CreateCategoryInput;
import com.habsida.morago.model.inputs.UpdateCategoryInput;
import com.habsida.morago.serviceImpl.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
@RequiredArgsConstructor
public class CategoryResolver {
    private final CategoryService categoryService;


    public Category createCategory( CreateCategoryInput input) {
        return categoryService.createCategory(input);
    }

    public Category updateCategory( UpdateCategoryInput input) {
        return categoryService.updateCategory(input);
    }

    public Boolean deleteCategoryById( Long id) {
        return categoryService.deleteCategoryById(id);
    }

    public Boolean changeCategoryStatus( Long id) {
        return categoryService.changeCategoryStatus(id);
    }

    public Set<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    public Category getCategoryById( Long id) {
        return categoryService.getCategoryById(id);
    }

    public Category getCategoryByName( String name) {
        return categoryService.getCategoryByName(name);
    }

    public Set<Category> getCategoryByStatus( Boolean status) {
        return categoryService.getCategoryByStatus(status);
    }
}
