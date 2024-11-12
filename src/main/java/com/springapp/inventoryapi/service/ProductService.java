package com.springapp.inventoryapi.service;

import com.springapp.inventoryapi.dto.OrderDto;
import com.springapp.inventoryapi.exception.ResourceNotFoundException;
import com.springapp.inventoryapi.model.Customer;
import com.springapp.inventoryapi.model.Product;
import com.springapp.inventoryapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<Product> searchProduct(String qString) {
        return productRepository.searchProduct(qString);
    }

    public Map<Integer, Boolean> placeOrderComputation(List<OrderDto> listDto) {
        Map<Integer,Boolean> map = new HashMap<>();

        listDto.parallelStream().forEach(dto -> {
            int pid = dto.getId();
            int quantityOrdered = dto.getQuantity();

            int availableQuantity = productRepository.findById(pid).get().getTotalQuantity();

            map.put(pid,availableQuantity>=quantityOrdered?true:false);
        });

        return map;
    }

    public void confirmOrder(List<OrderDto> listDto, Customer customer) {
        listDto.parallelStream().forEach(dto->{
            int pid = dto.getId();
            int quantityOrdered = dto.getQuantity();

            //fetch product
            Product product = productRepository.findById(pid).get();
            //set product total quantity
            product.setTotalQuantity(product.getTotalQuantity()-quantityOrdered);
            //update product table
            product = productRepository.save(product);


        });
    }
}
