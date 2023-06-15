package com.shop.entities;

import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

public class CartItem {
    private Integer productId;
    private String name;
    private double price;
    private String img;
    private int quantity = 1;

    public Integer   getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }




}
