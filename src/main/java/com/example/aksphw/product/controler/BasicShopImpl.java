package com.example.aksphw.product.controler;

import com.example.aksphw.product.GenerateProducts;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("Basic")
public class BasicShopImpl implements Shop {

    private final GenerateProducts generateProducts;

    public BasicShopImpl(GenerateProducts generateProducts) {
        this.generateProducts = generateProducts;
    }

    @Override
    public void get() {
        generateProducts.getProductList().forEach(System.out::println);
    }
}
