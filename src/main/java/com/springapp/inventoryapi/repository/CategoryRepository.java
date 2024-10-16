package com.springapp.inventoryapi.repository;

import com.springapp.inventoryapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
