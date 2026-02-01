package com.shopping.checkout;

public class Product {

    private int productId;
    private String name;
    private double price;
    private Offers offer;

    public Product(int productId, String name, double price, Offers offer) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.offer = offer;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Offers getOffer() {
        return offer;
    }

    public void setOffer(Offers offer) {
        this.offer = offer;
    }
}
