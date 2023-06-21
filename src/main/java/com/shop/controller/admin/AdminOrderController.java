package com.shop.controller.admin;

import com.shop.entities.Order;
import com.shop.repositories.OrderDetailRepository;
import com.shop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @GetMapping("")
    public String index(Model model,
                        @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        if(keyword != null && !keyword.trim().isEmpty())
        {
//            model.addAttribute("products", orderRepository.searchOrder(keyword.trim()));
        }else
        {
            model.addAttribute("products", orderRepository.findAll());
        }
        return "admin/order/index";
    }

    @GetMapping("/UpdateStatus")
    public String update(Model model, @RequestParam("type") Integer type, @RequestParam("id") Integer id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (type == 1) {
            assert order != null;
            order.setStatus(1);
            orderRepository.save(order);
        }
        if (type == 2) {
            assert order != null;
            order.setStatus(2);
            orderRepository.save(order);
        }


        return "redirect:/admin/order";
    }

    @GetMapping("/{id}")
    public String create(@PathVariable("id") Integer id, Model model) {


        // Replace db.getDetailOrdersByOrderId with your data access logic
//        List<CartItem> list = new ArrayList<>();

//        for (OrderDetail item : orderDetails) {
//            Product product = db.findProductById(item.getProductId()); // Replace db.findProductById with your data access logic
//            list.add(new CartItem(product, Integer.parseInt(item.getQuantity())));
//        }
        System.out.println(orderDetailRepository.findByOrders(orderRepository.findById(id).orElse(null)));
        model.addAttribute("model", orderDetailRepository.findByOrders(orderRepository.findById(id).orElse(null)));

        return "admin/order/detail";
    }
}
