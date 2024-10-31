package com.example.aksphw.product;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrzej.rzepecki
 **/

@Component
public class GenerateProducts {

    List<Product> productList = new ArrayList<>();

    public GenerateProducts(List<Product> productList) {
        productList.add(new Product("Banan"));
        productList.add(new Product("Jabłko"));
        productList.add(new Product("Mleko"));
        productList.add(new Product("Szynka"));
        productList.add(new Product("Miód"));
    }

    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public String toString() {
        return "GenerateProducts{" +
                "productList=" + productList +
                '}';
    }
}
