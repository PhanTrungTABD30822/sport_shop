package com.shop.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
public class ProductController
{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/product")
    public String index()
    {
        return "product/index";
    }
    @GetMapping("/product/{productId}")
    public String getDetailProduct(@PathVariable Integer productId, Model model) {
        List<Comment> listComments = commentRepository.findAllByProductId(productId);
        System.out.println(listComments);
        model.addAttribute("listComments", listComments);
        model.addAttribute("comment", new Comment());
        return "product/index";
    }

}
