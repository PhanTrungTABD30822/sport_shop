package com.shop.service;

import com.shop.entities.Category;
import com.shop.entities.Comment;
import com.shop.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CommentServiceImp implements ICommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public Optional<Comment> getCommentById(int idComment) {
        return null;
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
