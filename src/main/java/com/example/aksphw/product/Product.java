package com.example.aksphw.product;

import java.util.Random;

/**
 * @author andrzej.rzepecki
 **/


public class Product {

    public static final String FORMAT = "%,.2f";
    StringBuilder sb = new StringBuilder();
    private final Random random = new Random();
    private String productName;
    private final double productPrice;
    private double vatValue;
    private double vatPrice;
    private double discountValue;
    private double discountPrice;
    private double finalPrice;

    public Product(String productName) {
        this.productName = productName;
        this.productPrice = getRandomPrice();
        this.finalPrice = getProductPrice();
    }

    private int getRandomPrice() {

        return random.nextInt(300) + 50;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public double getVatValue() {
        return vatValue;
    }

    public void setVatValue(double vatValue) {
        this.vatValue = vatValue;
        this.setVatPrice(getFinalPrice() * getVatValue());
    }

    public double getVatPrice() {
        return vatPrice;
    }

    public void setVatPrice(double vatPrice) {
        this.vatPrice = vatPrice;
        this.setFinalPrice(getFinalPrice() + getVatPrice());
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
        this.setDiscountPrice(getProductPrice() - getDiscountValue());
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
        this.setFinalPrice(this.getProductPrice() - this.getDiscountPrice());
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Override
    public String toString() {
        sb.append("Product{" +
                "productName='" + getProductName() + "'" +
                ", productPrice=" + String.format(FORMAT,getProductPrice()));
        if (vatValue != 0.0) {
            sb.append(", vatValue=" + String.format(FORMAT,getVatValue()) +
                    ", vatPrice=" + String.format(FORMAT,getVatPrice()));
        }
        if (discountValue != 0.0) {
            sb.append(
                    ", discountValue=" + String.format(FORMAT,getDiscountValue()) +
                            ", discountPrice=" + String.format(FORMAT,getDiscountPrice()));
        }
        sb.append(", finalPrice=" + String.format(FORMAT,getFinalPrice()));
        return sb + "}";
    }
}
