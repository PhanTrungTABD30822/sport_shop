package com.shop.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.User;


import java.io.Serializable;

@Data
@Entity
@Table(name = "comment")
public class Comment  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;
    private Integer star;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}