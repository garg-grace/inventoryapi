package com.springapp.inventoryapi.service;

import com.springapp.inventoryapi.model.Category;
import com.springapp.inventoryapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
