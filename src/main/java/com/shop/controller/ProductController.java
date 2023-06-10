package com.shop.controller;

import com.shop.entities.Product;
import com.shop.repositories.ProductRepository;
import com.shop.service.ProductService;
import com.shop.entities.Comment;
import com.shop.entities.Customer;
import com.shop.repositories.CommentRepository;
import com.shop.repositories.CustomerRepository;
import com.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @GetMapping("/product")
    public String index(Model model) {

        return "product/index";
    }

    @GetMapping("/product/{productId}")
    public String getDetailIdProduct(@PathVariable("productId") Integer productId, Model model) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            model.addAttribute("product", product);
            model.addAttribute("products", productRepository.findAll());
            List<Comment> listComments = commentRepository.findAllByProductId(productId);
            System.out.println(listComments);
            model.addAttribute("listComments", listComments);
            model.addAttribute("comment", new Comment());
            return "product/index";
        }else{
            return "home/index";
        }

    }
}
