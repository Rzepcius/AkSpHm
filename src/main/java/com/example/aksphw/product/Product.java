package com.example.aksphw.product;

import java.util.Random;

/**
 * @author andrzej.rzepecki
 **/


public class Product {
    private final Random random = new Random();
    private String productName;
    private final double productPrice;
    private double vatAmount;
    private double vatPrice;
    private double discountAmount;
    private double discountPrice;

    public Product(String productName) {
        this.productName = productName;
        this.productPrice = getRandomPrice();
        this.vatAmount = 0.0;
        this.vatPrice = 0.0;
        this.discountAmount = 0.0;
        this.discountPrice = 0.0;
    }

    private int getRandomPrice() {

        return random.nextInt(300) + 50;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public double getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(double vatAmount) {
        this.vatAmount = vatAmount;
    }

    public double getVatPrice() {
        return vatPrice;
    }

    public void setVatPrice(double vatPrice) {
        this.vatPrice = vatPrice;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "random=" + random +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", vatAmount=" + vatAmount +
                ", vatPrice=" + vatPrice +
                ", discountAmount=" + discountAmount +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
