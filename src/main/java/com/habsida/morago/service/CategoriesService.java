package com.habsida.morago.service;


import com.habsida.morago.model.entity.Categories;
import com.habsida.morago.repository.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;


@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoriesRepository repository;
    public Categories create(Categories categories) {
        return repository.save(categories);
    }

    public Categories getById(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Categories update(Long id, String name, Boolean is_active){
        Categories categories = getById(id);
        categories.setName(name);
        categories.setIs_active(is_active);
        return repository.saveAndFlush(categories);
    }

    public Boolean deleteById(Long id){
        repository.deleteById(id);
        return true;
    }
}
