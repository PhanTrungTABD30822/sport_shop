package com.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {
    @GetMapping("")
    public String index() {
        return "admin/category/index";
    }

    @GetMapping("/create")
    public String create() {
        return "admin/category/create";
    }

    @PostMapping("/create")
    public String save() {
        return "admin/category/create";
    }

    @GetMapping("/edit")
    public String edit() {
        return "admin/category/edit";
    }


    @PostMapping("/edit")
    public String update() {
        return "admin/category/edit";
    }
}
