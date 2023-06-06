package com.shop.controller;

import com.shop.entities.Comment;
import com.shop.entities.Customer;
import com.shop.entities.Product;
import com.shop.repositories.CommentRepository;
import com.shop.repositories.CustomerRepository;
import com.shop.repositories.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/product/comments/{productId}")
    public String createComment(@PathVariable("productId") Integer productId, @ModelAttribute("comment") Comment comment, Principal principal) {

        System.out.println(principal.getName());


        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Product not found with id: " + productId);
        }
        Product product = productOptional.get();

        Customer customer = customerRepository.findByEmail("123@gmail.com");
        if (customer==null) {
            throw new RuntimeException("Customer not found with id: " + productId);
        }
        comment.setProduct(product);
        comment.setCustomer(customer);
        comment.setContent(comment.getContent());
        comment.setStar(comment.getStar());
        commentRepository.save(comment);
        return "redirect:/product/{productId}";
    }
    @ResponseBody
    @PostMapping("/api/comment")
    public String createComment(HttpServletRequest httpServletRequest, Principal principal) {
        Optional<Product> productOptional = productRepository.findById(Integer.valueOf(httpServletRequest.getParameter("productId")));
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Product not found with id: " + httpServletRequest.getParameter("content"));
        }
        Product product = productOptional.get();
        if (principal == null)
        {
            throw new RuntimeException("You are not logged into your account, please login before comment");
        }
        System.out.println("Show log principal: " + principal.getName());
        String email = principal.getName();
        Customer customer = customerRepository.findByEmail(email);
        if (customer==null) {
            throw new RuntimeException("Customer not found with id: " + httpServletRequest.getParameter("content"));
        }
        Comment comment=new Comment();
        comment.setProduct(product);
        comment.setCustomer(customer);
        comment.setContent((String) httpServletRequest.getParameter("content"));
        comment.setStar(Integer.valueOf(httpServletRequest.getParameter("star")));
        commentRepository.save(comment);
        return customer.getName();
    }

    @PostMapping("product/{productId}/comments/{commentId}")
    public Comment updateComment(@PathVariable Integer commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (!optionalComment.isPresent()) {
            throw new RuntimeException("Comment is not found in database");
        }
        Comment comment = optionalComment.get();
        comment.setContent(comment.getContent());
        return null;
    }

    @DeleteMapping("/product/{productId}/comments/{commentId}")
    public String deletedComment(@PathVariable Integer commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (!optionalComment.isPresent()) {
            throw new RuntimeException("Comment is not found in database");
        }
        Comment comment = optionalComment.get();
        commentRepository.delete(comment);
        return "product/index";
    }

}

