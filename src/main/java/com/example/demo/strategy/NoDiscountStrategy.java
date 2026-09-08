package com.example.demo.strategy;

import org.springframework.stereotype.Component;

@Component("NO_DISCOUNT")
public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice;
    }
}