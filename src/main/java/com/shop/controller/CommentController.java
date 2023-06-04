package com.shop.controller;

import com.shop.entities.Comment;
import com.shop.entities.Customer;
import com.shop.entities.Product;
import com.shop.repositories.CommentRepository;
import com.shop.repositories.CustomerRepository;
import com.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String createComment(@PathVariable("productId") Integer productId, @ModelAttribute("comment") Comment comment)
    {


        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty())
        {
            throw new RuntimeException("Product not found with id: " + productId);
        }
        Product product = productOptional.get();

        Optional<Customer> customerOptional = customerRepository.findById(2);
        if(customerOptional.isEmpty())
        {
            throw new RuntimeException("Customer not found with id: " + productId);
        }
        Customer customer = customerOptional.get();
        comment.setProduct(product);
        comment.setCustomer(customer);
        comment.setContent(comment.getContent());
        comment.setStar(comment.getStar());
        commentRepository.save(comment);
        return "redirect:/product/{productId}";
    }
    @PostMapping("product/{productId}/comments/{commentId}")
    public Comment updateComment(@PathVariable Integer commentId)
    {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (!optionalComment.isPresent())
        {
            throw new RuntimeException("Comment is not found in database");
        }
        Comment comment = optionalComment.get();
        comment.setContent(comment.getContent());
        return  null;
    }

    @DeleteMapping("/product/{productId}/comments/{commentId}")
    public String deletedComment(@PathVariable Integer commentId)
    {
        Optional<Comment> optionalComment =  commentRepository.findById(commentId);
        if(!optionalComment.isPresent())
        {
            throw  new RuntimeException("Comment is not found in database");
        }
        Comment comment = optionalComment.get();
        commentRepository.delete(comment);
        return "product/index";
    }
}

