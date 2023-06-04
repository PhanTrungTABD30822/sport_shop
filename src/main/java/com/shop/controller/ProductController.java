package com.shop.controller;

import com.shop.entities.Product;
import com.shop.repositories.ProductRepository;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public String index() {
        return "product/index";
    }

    @GetMapping("/product/{productId}")
    public String getDetailIdProduct(@PathVariable("productId") Long productId, Model model) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            model.addAttribute("product", product);
            return "product/index";
        }else{
            return "home/index";
        }
    }

}
