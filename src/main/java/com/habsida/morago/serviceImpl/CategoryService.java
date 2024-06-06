package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GraphqlExceptionHandler;
import com.habsida.morago.model.entity.Category;
import com.habsida.morago.model.inputs.CreateCategoryInput;
import com.habsida.morago.model.inputs.UpdateCategoryInput;
import com.habsida.morago.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.checkerframework.common.aliasing.qual.Unique;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    public Category createCategory(CreateCategoryInput input) {
        if (existsUserByName(input.getName())) {
            throw new GraphqlExceptionHandler("This category name (" + input.getName() + ") already exists");
        }
        Category category = new Category();
        category.setName(input.getName());
        category.setIs_active(true);
        return repository.save(category);
    }

    public List<Category> getAllCategories(){
        return repository.findAll();
    }
    public Category getCategoryById(Long id){
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public Category getCategoryByName(String name) {
        return repository.findByName(name).orElseThrow(EntityNotFoundException::new);
    }
    @Transactional(rollbackFor = RuntimeException.class)
    public Category updateCategory(UpdateCategoryInput input){
        Category category = getCategoryById(input.getId());
        if (input.getName() != null && !input.getName().isEmpty()) {
            if (existsUserByName(input.getName())) {
                throw new GraphqlExceptionHandler("This category name (" + input.getName() + ") already exists");
            }
            category.setName(input.getName());
        }
        if (input.getIs_active() != null) {
            category.setIs_active(input.getIs_active());
        }
        return repository.saveAndFlush(category);
    }

    public Boolean deleteCategoryById(Long id){
        repository.deleteById(id);
        return true;
    }

    public Boolean existsUserByName(String name) {
        return repository.existsByName(name.replaceAll("-", "").trim());
    }
}
