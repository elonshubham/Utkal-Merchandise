package com.example.utkalmerchandise;

public class ProductResult {

    String productname;
    String price;
    String imageurl;

    public ProductResult(String productname, String price,String imageurl){

        this.productname=productname;
        this.price=price;
        this.imageurl=imageurl;
    }

    public String getProductname() {
        return productname;
    }

    public String getPrice() {
        return price;
    }

    public String getImageurl() {
        return imageurl;
    }

}
