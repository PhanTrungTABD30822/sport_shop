package com.shop.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;


import java.io.Serializable;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "total_price")
    private String totalPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNameReceive() {
        return nameReceive;
    }

    public void setNameReceive(String nameReceive) {
        this.nameReceive = nameReceive;
    }

    public String getAddressReceive() {
        return addressReceive;
    }

    public void setAddressReceive(String addressReceive) {
        this.addressReceive = addressReceive;
    }

    public String getPhoneReceive() {
        return phoneReceive;
    }

    public void setPhoneReceive(String phoneReceive) {
        this.phoneReceive = phoneReceive;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "name_receive")
    private String nameReceive;
    @Column(name = "address_receive")
    private String addressReceive;
    @Column(name = "phone_receive")
    private String phoneReceive;
    @Column(name = "note")
    private String note;
    @Column(name = "status")
    private Integer status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<OrderDetails> orderDetails;
}
