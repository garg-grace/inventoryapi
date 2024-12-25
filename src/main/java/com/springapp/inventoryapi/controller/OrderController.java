package com.springapp.inventoryapi.controller;

import com.springapp.inventoryapi.dto.MessageDto;
import com.springapp.inventoryapi.dto.OrderSheetDto;
import com.springapp.inventoryapi.enums.OrderStatus;
import com.springapp.inventoryapi.exception.ResourceNotFoundException;
import com.springapp.inventoryapi.model.Order;
import com.springapp.inventoryapi.model.Product;
import com.springapp.inventoryapi.model.Supplier;
import com.springapp.inventoryapi.model.Warehouse;
import com.springapp.inventoryapi.service.OrderService;
import com.springapp.inventoryapi.service.ProductService;
import com.springapp.inventoryapi.service.SupplierService;
import com.springapp.inventoryapi.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = {"http://localhost:3000/"})
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private SupplierService supplierService;

    @PostMapping("/entry")
    public ResponseEntity<?> generateOrder(@RequestBody OrderSheetDto dto, Order order){
        //step1 : take input from user
        int productId = dto.getProductId();
        int warehouseId = dto.getWarehouseId();
        int supplierId = dto.getSupplierId();
        int quantity = dto.getQuantity();

        //step2 : fetch obbjects based on ids of product,warehouse,supplier
        Product product;
        try {
            product = productService.getById(productId);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto("product id invalid"));
        }

        Warehouse warehouse = warehouseService.getById(warehouseId);

        Supplier supplier = supplierService.getById(supplierId);

        //step3: create order object, attach above objects to order and save it
        order.setQuantity(quantity);
        order.setDateOfOrder(LocalDate.now());
        order.setProduct(product);
        order.setWarehouse(warehouse);
        order.setSupplier(supplier);
        order.setOrderStatus(OrderStatus.PENDING);

        order = orderService.insert(order);

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @GetMapping("/all")
    public List<Order> getAll(@RequestParam("page") Integer page,
                              @RequestParam("size") Integer size,
                              Principal principal){
        Pageable pageable = PageRequest.of(page,size);
        String supplierUsername = principal.getName();
        Supplier supplier = supplierService.getByUsername(supplierUsername);
        List<Order> list =orderService.getAll(pageable,supplier.getId());

        return list;
    }

    @PutMapping("/update/{oid}/{status}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable("oid") int oid,
                                                        @PathVariable("status") String status){
        return orderService.updateOrderStatus(oid,status);
    }
}
