package com.shop.entities;


import com.shop.common.UserRole;
import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "customer")
public class Customer  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String phone;
    private String address;
    private String email;
    private String password;
    private String name;

    private Integer gender;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "customer")
    private List<Comment> comments;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
