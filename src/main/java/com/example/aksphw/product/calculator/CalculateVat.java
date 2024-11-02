package com.example.aksphw.product.calculator;

import com.example.aksphw.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateVat implements Calculate {

    @Value("${shop.vat.type:N/A}")
    private String vatType;
    @Value("${shop.vat.amount}")
    private double vatAmount;
    @Value("${shop.vat.percentageAmount}")
    private double vatPercentage;

    Logger logger = LoggerFactory.getLogger(CalculateVat.class);

    @Override
    public void calculate(List<Product> products) {
        if (!vatType.equals("N/A")) {
            if (vatType.equals("percentage")) {
                products.forEach(p -> p.setVatValue((vatPercentage / 100)));
            } else if (vatType.equals("amount")) {
                products.forEach(p -> p.setVatValue((vatAmount)));
            } else {
                logger.info("Invalid Vat Amount Type");
            }
        } else {
            logger.info("Invalid Vat Amount Type");
        }
    }
}
