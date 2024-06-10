package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Category;
import com.habsida.morago.model.inputs.CreateCategoryInput;
import com.habsida.morago.model.inputs.UpdateCategoryInput;
import com.habsida.morago.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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
        category.setName(input.getName());
        category.setIsActive(input.getIsActive());
        return repository.save(category);
    }

    public Set<Category> getAllCategories() {
        return new HashSet<>(repository.findAll());
    }

    public Category getCategoryById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Category getCategoryByName(String name) {
        return repository.findByName(name).orElseThrow(EntityNotFoundException::new);
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
        repository.deleteById(id);
        return true;
    }

    public Boolean existsUserByName(String name) {
        return repository.existsByName(name.replaceAll("-", "").trim());
    }

    public Set<Category> getCategoryByStatus(Boolean status) {
        return repository.findByIsActive(status);
    }

    public Boolean changeCategoryStatus(Long categoryId) {
        Category category = repository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
        category.setIsActive(!category.getIsActive());
        repository.save(category);
        return true;
    }
}
