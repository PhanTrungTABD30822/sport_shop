package com.shop.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@Entity

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;
    private String img;
    @Column(columnDefinition = "TEXT")
    private String description;


    @OneToMany(mappedBy = "product")
    private List<Comment> comments;
    @OneToMany(mappedBy = "product")
    private List<OrderDetails> orderDetails;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
}
