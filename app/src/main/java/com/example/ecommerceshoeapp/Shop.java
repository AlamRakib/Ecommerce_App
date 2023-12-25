package com.example.ecommerceshoeapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Shop {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "product_name")
    public String productName;

    @ColumnInfo(name = "discount")
    public double discount;

    @ColumnInfo(name = "price")
    public long price;

    public Shop(String productName, double discount, long price) {
        this.uid = uid;
        this.productName = productName;
        this.discount = discount;
        this.price = price;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
