package com.shop.entities;


import com.shop.common.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


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
    @Size(max = 10, message = "Số điện thoại chỉ được 10 số")
    @NotBlank(message = "Số điện thoại không được để trống! ")
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @NotBlank(message = "Email không được để trống! ")
    @Size(max = 50, message = "Email không quá 50 ký tự")
    private String email;
    @NotBlank(message = "Password không được để trống")
    private String password;
    @Size(max = 50, message = "Tên không quá 50 ký tự")
    @NotBlank(message = "Your name is required")
    private String name;

    private Integer gender;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Comment> comments;
    @OneToMany(mappedBy = "customer")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Order> orders;
}
