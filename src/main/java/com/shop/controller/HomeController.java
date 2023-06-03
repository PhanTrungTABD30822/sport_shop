package com.shop.controller;

import com.shop.common.UserRole;
import com.shop.entities.Customer;
import com.shop.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String home() {
        Authentication auth = SecurityContextHolder. getContext(). getAuthentication();
        UserDetails user= (UserDetails) auth.getPrincipal();
        System.out.println(user);
        return "home/index";
    }

    @GetMapping("/login")
    public String login() {
        return "home/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("customer",new Customer());
        return "home/register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model){
        System.out.println(bindingResult);

        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(error
                    -> model.addAttribute(error.getField()+"_error", error.getDefaultMessage()));
            return "home/register";
        }


        customer.setRole(UserRole.USER);
        customer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
        System.out.println(customer);
        customerService.save(customer);
        return "redirect:/login";
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
