package com.springapp.inventoryapi.repository;

import com.springapp.inventoryapi.model.InwardRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InwardRegisterRepository extends JpaRepository<InwardRegister,Integer> {
}
