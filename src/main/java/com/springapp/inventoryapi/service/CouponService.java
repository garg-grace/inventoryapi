package com.springapp.inventoryapi.service;

import com.springapp.inventoryapi.exception.InvalidCouponCode;
import com.springapp.inventoryapi.model.Coupon;
import com.springapp.inventoryapi.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    public Coupon insert(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public List<Coupon> getAll() {
        return null;
    }


    public Coupon verify(double totalAmount, String couponCode) throws InvalidCouponCode {
        Coupon coupon = couponRepository.findByCouponCode(couponCode);
        if(coupon.getTotalAmount()<=totalAmount)
            return coupon;

        throw new InvalidCouponCode("Invalid coupon code selected");
    }
}
