package com.springapp.inventoryapi.repository;

import com.springapp.inventoryapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select p from Product p where p.title LIKE %?1%")
    List<Product> searchProduct(String qString);
}
