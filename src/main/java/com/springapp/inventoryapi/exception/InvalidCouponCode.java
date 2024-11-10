package com.springapp.inventoryapi.exception;

public class InvalidCouponCode extends Exception {
    private static final long serialVersionUID = 1L;
    private String message;

    public InvalidCouponCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
