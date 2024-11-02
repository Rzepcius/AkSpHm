package com.example.aksphw.product.calculator;

import com.example.aksphw.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateDiscount implements Calculate {

    @Value("${shop.discount.type:N/A}")
    private String discountType;
    @Value("${shop.discount.amount}")
    private double discountAmount;
    @Value("${shop.discount.percentageAmount}")
    private double discountPercentage;

    private CalculateVat calculateVat;

    Logger logger = LoggerFactory.getLogger(CalculateDiscount.class);

    public CalculateDiscount(CalculateVat calculateVat) {
        this.calculateVat = calculateVat;
    }

    @Override
    public void calculate(List<Product> products) {
        if (!discountType.equals("N/A")) {
            if (discountType.equals("percentage")) {
                products.stream().forEach(p -> p.setDiscountValue((discountPercentage / 100)));
            } else if (discountType.equals("amount")) {
                products.stream().forEach(p -> p.setDiscountValue((discountAmount)));
            } else {
                logger.info("Invalid Discount Amount Type");
                return;
            }
            calculateVat.calculate(products);
        } else {
            logger.info("Invalid Discount Amount Type");
        }
    }
}
