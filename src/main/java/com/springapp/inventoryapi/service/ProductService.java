package com.springapp.inventoryapi.service;

import com.springapp.inventoryapi.exception.ResourceNotFoundException;
import com.springapp.inventoryapi.model.Category;
import com.springapp.inventoryapi.model.Product;
import com.springapp.inventoryapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public void deleteProduct(int pid) {
        productRepository.deleteById(pid);
    }

    public void insert(Product product) {
        productRepository.save(product);
    }

    public Product getById(int pid) throws ResourceNotFoundException {
        Optional<Product> optional = productRepository.findById(pid);
        if(!optional.isPresent()) throw new ResourceNotFoundException("Product ID Invalid");
        return optional.get();
    }
}
