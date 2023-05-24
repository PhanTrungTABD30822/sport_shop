package com.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {
    @GetMapping("/home")
    public String homeAdmin(){
        return "admin/home/indexAdmin";
    }
    @GetMapping("/showForm")
    public String showForm(){
        return "admin/home/Form";
    }
}
