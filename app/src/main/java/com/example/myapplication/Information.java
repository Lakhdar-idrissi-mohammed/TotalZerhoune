package com.example.myapplication;

public class Information {
    String ProductName;
    int stock;

    public Information() {
    }

    public Information(String productName, int stock) {
        ProductName = productName;
        this.stock = stock;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
