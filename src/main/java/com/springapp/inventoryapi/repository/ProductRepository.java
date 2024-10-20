package com.springapp.inventoryapi.repository;

import com.springapp.inventoryapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
