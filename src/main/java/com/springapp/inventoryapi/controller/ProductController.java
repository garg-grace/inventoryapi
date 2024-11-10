package com.springapp.inventoryapi.controller;

import com.springapp.inventoryapi.dto.MessageDto;
import com.springapp.inventoryapi.exception.ResourceNotFoundException;
import com.springapp.inventoryapi.model.Category;
import com.springapp.inventoryapi.model.Product;
import com.springapp.inventoryapi.service.CategoryService;
import com.springapp.inventoryapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/add/{catID}")
    public ResponseEntity<?> insertProduct(@PathVariable("catID") int catID,@RequestBody Product product){

        try {
            Category category = categoryService.getById(catID);
            product.setCategory(category);
            productService.insert(product);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto(e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("Product inserted.."));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        List<Product> list = productService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @DeleteMapping("/delete/{pid}")
    public ResponseEntity<?> deleteProduct(@PathVariable("pid") int pid){
        productService.deleteProduct(pid);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("product deleted"));

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@PathVariable("pid") int pid , @RequestBody Product updatedProduct){
        try {
            Product prooductDB = productService.getById(pid);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto(e.getMessage()));
        }
        updatedProduct.setId(pid);
        productService.insert(updatedProduct);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("Product updated.."));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") int id){
        try {
            Product product = productService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto(e.getMessage()));
        }

    }

    @GetMapping("/search/{qString}")
    public List<Product> searchProduct(@PathVariable("qString") String qString){
        List<Product> list=productService.searchProduct(qString);
        return list;

    }
}
