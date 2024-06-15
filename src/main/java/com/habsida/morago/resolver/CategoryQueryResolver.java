package com.habsida.morago.resolver;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.entity.Category;
import com.habsida.morago.serviceImpl.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@RequiredArgsConstructor
@Component
public class CategoryQueryResolver implements GraphQLQueryResolver {
    private final CategoryService categoryService;

    public Set<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    public Category getCategoryById(Long id) {
        return categoryService.getCategoryById(id);
    }

    public Category getCategoryByName(String name) {
        return categoryService.getCategoryByName(name);
    }

    public Set<Category> getCategoryByStatus(Boolean status) {
        return categoryService.getCategoryByStatus(status);
    }

}
