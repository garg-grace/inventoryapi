package com.springapp.inventoryapi.controller;

import com.springapp.inventoryapi.dto.MessageDto;
import com.springapp.inventoryapi.exception.ResourceNotFoundException;
import com.springapp.inventoryapi.model.Category;
import com.springapp.inventoryapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Category insertCategory(@RequestBody Category category){
        category = categoryService.insert(category);
        return category;

    }

    @GetMapping("/all")
    public List<Category> getAllCategories(){
        List<Category> list = categoryService.getAllCategories();
        return list;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") int id , @RequestBody Category updatedCategory){
        //step1: fetch category object on basis of id
        Category categoryDb = null;
        try {
            categoryDb = categoryService.getById(id);
            categoryDb.setName(updatedCategory.getName());
            categoryDb.setPriority(updatedCategory.getPriority());
            categoryDb = categoryService.insert(categoryDb);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto(e.getMessage()));
            //e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).body(categoryDb);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") int id){
        Category categoryDb = null;
        try {
            categoryDb = categoryService.getById(id);
            categoryService.delete(categoryDb);
            return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("Category Deleted"));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto(e.getMessage()));
        }

    }



}
