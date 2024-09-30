package com.vicoupon.common.models;

public class CrawlerResult {
    private String productName;
    private double productPrice;
    private String productUrl;

    public CrawlerResult(String productName, double productPrice, String productUrl) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productUrl = productUrl;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getProductUrl() {
        return productUrl;
    }

    @Override
    public String toString() {
        return "Product: " + productName + ", Price: " + productPrice + ", URL: " + productUrl;
    }
}
