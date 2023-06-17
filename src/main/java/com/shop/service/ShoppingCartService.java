package com.shop.service;

import com.shop.entities.CartItem;
import jakarta.servlet.http.HttpSession;

import java.util.Collection;

public interface ShoppingCartService {
    void add(CartItem newItem);
    void remove(Integer id);
    CartItem update(Integer productID, int quantity);
    void clear();
    double getAmount();
    int getCount();
    Collection<CartItem> getAllItems();
}
