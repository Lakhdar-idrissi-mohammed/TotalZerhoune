package com.example.myapplication;

public class MyProductsData {
    private String productName;

    private Integer productImage;
    private String Quantity;

    public MyProductsData(String productName, String elegantNumberButton) {
        this.productName = productName;
        this.Quantity = elegantNumberButton;
    }

    public MyProductsData() {
    }

    public MyProductsData(String productName, Integer productImage) {
        this.productName = productName;

        this.productImage = productImage;
    }



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }



    public Integer getProductImage() {
        return productImage;
    }

    public void setProductImage(Integer productImage) {
        this.productImage = productImage;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        this.Quantity = quantity;
    }
}


