package com.springapp.inventoryapi.controller;

import com.springapp.inventoryapi.model.Category;
import com.springapp.inventoryapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category/add")
    public Category insertCategory(@RequestBody Category category){
        category = categoryService.insert(category);
        return category;

    }

    @GetMapping("/category/all")
    public List<Category> getAllCategories(){
        List<Category> list = categoryService.getAllCategories();
        return list;
    }


}
