package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Category;
import com.habsida.morago.model.inputs.CreateCategoryInput;
import com.habsida.morago.model.inputs.UpdateCategoryInput;
import com.habsida.morago.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    public Category createCategory(CreateCategoryInput input) {
        Category category = new Category();
        category.setName(input.getName());
        category.setIs_active(true);
        return repository.save(category);
    }

    public List<Category> getAllCategories(){
        return repository.findAll();
    }
    public Category getCategoryById(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }
    public Category getCategoryByName(String name) {
        return repository.findByName(name).orElseThrow(EntityNotFoundException::new);
    }

    public Category updateCategory(UpdateCategoryInput input){
        Category category = getCategoryById(input.getId());
        category.setName(input.getName());
        category.setIs_active(input.getIs_active());
        return repository.saveAndFlush(category);
    }

    public Boolean deleteCategoryById(Long id){
        repository.deleteById(id);
        return true;
    }
}
