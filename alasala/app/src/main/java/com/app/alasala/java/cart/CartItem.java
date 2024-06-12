package com.app.alasala.java.cart;

public class CartItem {


    private String Name;
    private int Price;
    private String Time;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }


    private int imageResId;

    public CartItem(String Name, int Price, String Time, int imageResId) {
        this.Name = Name;
        this.Price = Price;
        this.Time = Time;
        this.imageResId = imageResId;
    }


}

