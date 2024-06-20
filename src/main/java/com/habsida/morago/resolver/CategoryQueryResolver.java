package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.entity.Category;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.serviceImpl.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategoryQueryResolver implements GraphQLQueryResolver {
    private final CategoryService categoryService;

    public PageOutput<Category> getAllCategoriesByPaging(PagingInput input) {
        return categoryService.getAllCategories(input);
    }

    public Category getCategoryById(Long id) {
        return categoryService.getCategoryById(id);
    }

    public Category getCategoryByName(String name) {
        return categoryService.getCategoryByName(name);
    }

    public PageOutput<Category> getCategoryByStatusIsActiveByPaging(PagingInput input) {
        return categoryService.getCategoryByStatusIsActive(input);
    }

}
