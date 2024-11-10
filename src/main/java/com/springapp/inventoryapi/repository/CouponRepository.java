package com.springapp.inventoryapi.repository;

import com.springapp.inventoryapi.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    Coupon findByCouponCode(String couponCode);
}
