package com.springapp.inventoryapi.repository;

import com.springapp.inventoryapi.model.Executive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutiveRepository extends JpaRepository<Executive,Integer> {
}
