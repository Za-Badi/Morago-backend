package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.entity.AppVersion;
import com.habsida.morago.model.entity.Category;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.inputs.CreateCategoryInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.inputs.UpdateCategoryInput;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.repository.CategoryRepository;

import javax.persistence.EntityNotFoundException;

import com.habsida.morago.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public Category createCategory(CreateCategoryInput input) {
        Category category = new Category();
        if (!input.getName().isEmpty()) {
            category.setName(input.getName());
        } else {
            throw new ExceptionGraphql("Please enter valid name");
        }
        category.setIsActive(input.getIsActive());
        return repository.save(category);
    }

    public PageOutput<Category> getAllCategories(PagingInput input) {
        Page<Category> pageRequest = repository.findAll(PageUtil.buildPageable(input));
        return PageUtil.buildPage(pageRequest);
    }

    public Category getCategoryById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Category with ID: " + id));
    }

    public Category getCategoryByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new EntityNotFoundException("No Category with name: " + name));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Category updateCategory(UpdateCategoryInput input) {
        Category category = getCategoryById(input.getId());
        if (input.getName() != null && !input.getName().isEmpty()) {
            category.setName(input.getName());
        }
        if (input.getIsActive() != null) {
            category.setIsActive(input.getIsActive());
        }
        return repository.saveAndFlush(category);
    }

    public Boolean deleteCategoryById(Long id) {
        Category category = getCategoryById(id);
        repository.delete(category);
        return true;
    }

    public Boolean existsUserByName(String name) {
        return repository.existsByName(name.replaceAll("-", "").trim());
    }

    public PageOutput<Category> getCategoryByStatusIsActive(PagingInput input) {
        Page<Category> pagedRequest = repository.findByIsActive(PageUtil.buildPageable(input));
        return PageUtil.buildPage(pagedRequest);
    }

    public Boolean changeCategoryStatus(Long categoryId) {
        Category category = repository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Category Not found with ID: " + categoryId));
        category.setIsActive(!category.getIsActive());
        repository.save(category);
        return true;
    }
}
