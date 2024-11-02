package com.example.aksphw.product.controler;

import com.example.aksphw.product.GenerateProducts;
import com.example.aksphw.product.calculator.CalculateDiscount;
import com.example.aksphw.product.calculator.CalculateVat;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("Pro")
public class ProShopImpl implements Shop {

    private final GenerateProducts generateProducts;
    private final CalculateDiscount calculateDiscount;

    public ProShopImpl(GenerateProducts generateProducts, CalculateDiscount calculateDiscount) {
        this.generateProducts = generateProducts;
        this.calculateDiscount = calculateDiscount;
    }

    @Override
    public void get() {
        calculateDiscount.calculate(generateProducts.getProductList());
        generateProducts.getProductList().forEach(System.out::println);
    }
}
