package com.springapp.inventoryapi.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String location;

    @ManyToMany
    @JoinTable(name = "warehouse_product",
               joinColumns=@JoinColumn(name="warehouse_id"),
               inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", products=" + products +
                '}';
    }
}
