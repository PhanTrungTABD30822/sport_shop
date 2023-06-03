package com.shop.controller;

import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ProductService productService;
    @GetMapping()
    public String index() {
        return "cart/index";
    }

    @GetMapping("/checkout")
    public String checkout(){
        return "cart/checkout";
    }
}
