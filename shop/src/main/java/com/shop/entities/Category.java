package com.shop.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;


@Data
@Entity

public class Category   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
