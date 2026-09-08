package com.example.demo.strategy;

import org.springframework.stereotype.Component;

@Component("MEMBER")
public class MemberDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.90; // ลด 10%
    }
}