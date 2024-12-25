package com.springapp.inventoryapi.controller;

import com.springapp.inventoryapi.dto.MessageDto;
import com.springapp.inventoryapi.dto.OrderDto;
import com.springapp.inventoryapi.exception.ResourceNotFoundException;
import com.springapp.inventoryapi.model.Category;
import com.springapp.inventoryapi.model.Customer;
import com.springapp.inventoryapi.model.OutwardRegister;
import com.springapp.inventoryapi.model.Product;
import com.springapp.inventoryapi.service.CategoryService;
import com.springapp.inventoryapi.service.CustomerService;
import com.springapp.inventoryapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = {"http://localhost:3000/"})
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerService customerService;

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

    @PostMapping("/place-order")
    private Map<Integer,Boolean> placeOrder(@RequestBody List<OrderDto> listDto){
        return productService.placeOrderComputation(listDto);
    }

    @PostMapping("/confirm-order")
    public void confirmOrder(@RequestBody List<OrderDto> listDto, Principal principal){
        String username = principal.getName();
        Customer customer = customerService.getCustomerByUsername(username);

        productService.confirmOrder(listDto,customer);

        //make entry in outward register
        //method implementation pending
    }

    @GetMapping("/category/all/{cid}")
    public List<Product> getAllByCategoryId(@PathVariable("cid") int cid){
        return productService.getAllByCategoryId(cid);
    }
}
