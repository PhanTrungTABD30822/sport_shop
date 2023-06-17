package com.shop.entities;

import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
public class CartItem {
    private Integer productId;
    private String name;
    private double price;
    private String img;
    private int quantity;
    private String size;


}
