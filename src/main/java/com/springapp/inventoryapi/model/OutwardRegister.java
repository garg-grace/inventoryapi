package com.springapp.inventoryapi.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name ="outward_register")
public class OutwardRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="invoice_no")
    private String invoiceNo;

    @Column(name="date_of_supply")
    private LocalDate dateOfSupply;

    @Column(name="date_of_delivery")
    private LocalDate dateOfDelivery;

    private int quantity;

    @OneToOne
    private Warehouse warehouse;

    @OneToOne
    private Customer customer;

    @OneToOne
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public LocalDate getDateOfSupply() {
        return dateOfSupply;
    }

    public void setDateOfSupply(LocalDate dateOfSupply) {
        this.dateOfSupply = dateOfSupply;
    }

    public LocalDate getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(LocalDate dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OutwardRegister{" +
                "id=" + id +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", dateOfSupply=" + dateOfSupply +
                ", dateOfDelivery=" + dateOfDelivery +
                ", quantity=" + quantity +
                ", warehouse=" + warehouse +
                ", customer=" + customer +
                ", product=" + product +
                '}';
    }
}
