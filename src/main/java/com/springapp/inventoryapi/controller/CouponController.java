package com.springapp.inventoryapi.controller;

import com.springapp.inventoryapi.exception.InvalidCouponCode;
import com.springapp.inventoryapi.model.Coupon;
import com.springapp.inventoryapi.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/add")
    public Coupon addCoupon(@RequestBody Coupon coupon){
        coupon = couponService.insert(coupon);
        return coupon;
    }

    @GetMapping("/all")
    public List<Coupon> getAll(){
        return couponService.getAll();

    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyCoupon(@RequestBody Coupon coupon){
        double totalAmount = coupon.getTotalAmount();
        String couponCode = coupon.getCouponCode();
        try {
            coupon = couponService.verify(totalAmount,couponCode);
            return ResponseEntity.status(HttpStatus.OK).body(coupon);
        } catch (InvalidCouponCode e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
}
