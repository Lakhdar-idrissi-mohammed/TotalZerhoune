package com.example.myapplication;

import android.net.Uri;

import java.net.URI;
import java.net.URL;

public class Information2 {
    String ProductName;
    String Code;
    String Image;

    public Information2(String productName, String code, String image) {
        ProductName = productName;
        Code = code;
        Image = image;
    }

    public Information2() {
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}

