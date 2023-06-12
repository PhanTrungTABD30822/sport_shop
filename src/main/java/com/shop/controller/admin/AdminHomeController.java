package com.shop.controller.admin;

import com.shop.repositories.CommentRepository;
import com.shop.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    public String login(){
        return "admin/login";
    }
    @GetMapping("/home")
    public String homeAdmin(){
        return "admin/home/index";
    }

}
