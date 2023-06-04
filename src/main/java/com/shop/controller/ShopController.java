package com.shop.controller;

import com.shop.entities.Product;
import com.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShopController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/shop")
    public String index(Model model){
        model.addAttribute("products", productRepository.findAll());
        return  "shop/index";
    }

}
