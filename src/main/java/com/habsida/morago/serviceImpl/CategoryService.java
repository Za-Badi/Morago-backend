package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.CategoryDTO;
import com.habsida.morago.model.entity.Category;
import com.habsida.morago.model.inputs.CreateCategoryInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.inputs.UpdateCategoryInput;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.repository.CategoryRepository;
import com.habsida.morago.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final ModelMapper modelMapper;



    public CategoryDTO createCategory(CreateCategoryInput input) {
        Category category = new Category();
        if (!input.getName().isEmpty()) {
            category.setName(input.getName());
        } else {
            throw new ExceptionGraphql("Please enter valid name");
        }
        category.setIsActive(input.getIsActive());
        Category savedCategory = repository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    public PageOutput<CategoryDTO> getAllCategories(PagingInput input) {
        Page<Category> pageRequest = repository.findAll(PageUtil.buildPageable(input));
        List<CategoryDTO> categoryDTOs = pageRequest.getContent()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
        return new PageOutput<>(pageRequest.getNumber(), pageRequest.getTotalPages(), pageRequest.getTotalElements(), categoryDTOs);
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = getCategoryByIdInternal(id);
        return modelMapper.map(category, CategoryDTO.class);
    }

    public CategoryDTO getCategoryByName(String name) {
        Category category = repository.findByName(name).orElseThrow(() -> new EntityNotFoundException("No Category with name: " + name));
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public CategoryDTO updateCategory(UpdateCategoryInput input) {
        Category category = getCategoryByIdInternal(input.getId());
        if (input.getName() != null && !input.getName().isEmpty()) {
            category.setName(input.getName());
        }
        if (input.getIsActive() != null) {
            category.setIsActive(input.getIsActive());
        }
        Category updatedCategory = repository.saveAndFlush(category);
        return modelMapper.map(updatedCategory, CategoryDTO.class);
    }

    public Boolean deleteCategoryById(Long id) {
        Category category = getCategoryByIdInternal(id);
        repository.delete(category);
        return true;
    }

    public Boolean existsUserByName(String name) {
        return repository.existsByName(name.replaceAll("-", "").trim());
    }

    public PageOutput<CategoryDTO> getCategoryByStatusIsActive(PagingInput input) {
        Page<Category> pagedRequest = repository.findByIsActive(PageUtil.buildPageable(input));
        List<CategoryDTO> categoryDTOs = pagedRequest.getContent()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
        return new PageOutput<>(pagedRequest.getNumber(), pagedRequest.getTotalPages(), pagedRequest.getTotalElements(), categoryDTOs);
    }

    public Boolean changeCategoryStatus(Long categoryId) {
        Category category = getCategoryByIdInternal(categoryId);
        category.setIsActive(!category.getIsActive());
        repository.save(category);
        return true;
    }

    private Category getCategoryByIdInternal(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Category with ID: " + id));
    }
}
