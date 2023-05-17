package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home() {
        return "home/index";
    }

    @GetMapping("login")
    public String login() {
        return "home/login";
    }

    @GetMapping("register")
    public String register() {
        return "home/register";
    }

    @GetMapping("about")
    public String about() {
        return "home/about";
    }

    @GetMapping("contact")
    public String contact() {
        return "home/contact";
    }
}
