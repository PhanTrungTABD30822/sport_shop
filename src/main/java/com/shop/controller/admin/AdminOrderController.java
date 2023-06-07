package com.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {
    @GetMapping("")
    public String index() {
        return "admin/order/index";
    }
    @GetMapping("/detail")
    public String create() {
        return "admin/order/detail";
    }
}
