package com.shop.controller.admin;

import com.shop.entities.Category;
import com.shop.entities.Comment;
import com.shop.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/comment")
public class AdminCommentController {
    @Autowired
    private CommentRepository commentRepository;


    @GetMapping("")
    public String index(Model model) {


        model.addAttribute("listComments", commentRepository.findAll());

        return "admin/comment/index";
    }

    @GetMapping("/delete/comment/{idComment}")
    public String deleteCommentByAdmin(@PathVariable("idComment") Integer idComment) {
        Comment comment = commentRepository.findById(idComment).orElseThrow(() -> new RuntimeException("Invalid id "));
        commentRepository.delete(comment);
        return "redirect:/admin/comment";
    }

}
