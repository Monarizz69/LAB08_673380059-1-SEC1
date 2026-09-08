package com.example.demo.strategy;

import org.springframework.stereotype.Component;

@Component("SEASONAL")
public class SeasonalSaleStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.80; // ลด 20%
    }
}