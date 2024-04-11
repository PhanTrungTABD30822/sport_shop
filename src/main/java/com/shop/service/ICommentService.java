package com.shop.service;

import com.shop.entities.Comment;
import com.shop.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ICommentService {
    public Optional<Comment> getCommentById(int idComment);
    List<Comment> getAllComments();
}
