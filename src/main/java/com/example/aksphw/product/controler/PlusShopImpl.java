package com.example.aksphw.product.controler;

import com.example.aksphw.product.GenerateProducts;
import com.example.aksphw.product.calculator.CalculateVat;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("Plus")
public class PlusShopImpl implements Shop {

    private GenerateProducts generateProducts;
    private CalculateVat calculateVat;

    public PlusShopImpl(GenerateProducts generateProducts, CalculateVat calculateVat) {
        this.generateProducts = generateProducts;
        this.calculateVat = calculateVat;
    }

    @Override
    public void get() {
        calculateVat.calculate(generateProducts.getProductList());
        generateProducts.getProductList().forEach(System.out::println);
    }
}
