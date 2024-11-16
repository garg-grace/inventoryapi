package com.springapp.inventoryapi.model;

import com.springapp.inventoryapi.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name ="inward_register")
public class InwardRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String invoice;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "date_of_supply")
    private LocalDate dateOfSupply;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Supplier supplier;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDateOfSupply() {
        return dateOfSupply;
    }

    public void setDateOfSupply(LocalDate dateOfSupply) {
        this.dateOfSupply = dateOfSupply;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "InwardRegister{" +
                "id=" + id +
                ", invoice='" + invoice + '\'' +
                ", quantity=" + quantity +
                ", orderStatus=" + orderStatus +
                ", dateOfSupply=" + dateOfSupply +
                ", product=" + product +
                ", warehouse=" + warehouse +
                ", supplier=" + supplier +
                '}';
    }
}
