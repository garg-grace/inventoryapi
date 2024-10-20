package com.springapp.inventoryapi.service;

import com.springapp.inventoryapi.exception.ResourceNotFoundException;
import com.springapp.inventoryapi.model.Category;
import com.springapp.inventoryapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public Category insert(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll().stream().sorted((c1,c2)-> c1.getPriority()- c2.getPriority()).collect(Collectors.toList());
    }

    public Category getById(int id) throws ResourceNotFoundException {
        Optional<Category> optional = categoryRepository.findById(id);
        if(!optional.isPresent()) throw new ResourceNotFoundException("Category ID Invalid");
        return optional.get();
    }

    public void delete(Category categoryDb) {
        categoryRepository.delete(categoryDb);
    }
}
