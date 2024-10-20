package com.springapp.inventoryapi.model;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    @Column(nullable = false)
    private String tagline;

    private double price;

    @Column(length = 1200)
    private String description;

    @ManyToOne
    private Category category;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTagline() {
        return tagline;
    }
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    @Override
    public String toString() {
        return "Product [id=" + id + ", title=" + title + ", tagline=" + tagline + ", price=" + price + ", description="
                + description + ", category=" + category + "]";
    }


}

