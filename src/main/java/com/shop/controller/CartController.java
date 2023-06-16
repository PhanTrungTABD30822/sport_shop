package com.shop.controller;

import com.shop.entities.*;
import com.shop.repositories.OrderDetailRepository;
import com.shop.repositories.OrderRepository;
import com.shop.repositories.ProductRepository;
import com.shop.service.CustomerService;
import com.shop.service.ProductService;
import com.shop.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller()
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ProductService productService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;
    @GetMapping()
    public String viewCart(Model model){
        model.addAttribute("all_items_in_shoppingcart",shoppingCartService.getAllItems());
        model.addAttribute("total_amount",shoppingCartService.getAmount());
        return "cart/index";
    }
    @GetMapping("add/{id}")
    public String addItem(@PathVariable("id") Integer id){
        Product product = productRepository.findById(id).orElse(null);
        if(product !=null){
            CartItem item = new CartItem();
            item.setProductId(product.getId());
            item.setName(product.getName());
            item.setImg(product.getImg());
            item.setPrice(product.getPrice());
            item.setQuantity(1);
            shoppingCartService.add(item);
        }
        return "redirect:/cart";
    }

    @GetMapping("clear")
    public String clearCart(){
        shoppingCartService.clear();
        return "redirect:/cart";
    }
    @GetMapping("remove/{id}")
    public String removeItem(@PathVariable("id") Integer id){
        shoppingCartService.remove(id);
        return "redirect:/cart";
    }
    @PostMapping("update")
    public String updateItem(@RequestParam("productId") Integer productId,@RequestParam("quantity")int  quantity){
        shoppingCartService.update(productId,quantity);
        return "redirect:/cart";
    }
    @GetMapping("/checkout")
    public String checkout(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }else {
            model.addAttribute("all_items_in_shoppingcart",shoppingCartService.getAllItems());
            model.addAttribute("total_amount",shoppingCartService.getAmount());
            model.addAttribute("order",new Order());

        }
        return "cart/checkout";
    }

    @PostMapping("/checkout")
    public String order(@ModelAttribute("order") Order order, Model model, Principal principal, HttpSession httpSession){
        if(principal == null){
            return "redirect:/login";
        }else {

            Customer customer = customerService.findById((Integer) httpSession.getAttribute("id"));
            customerService.save(customer);
            order.setCustomer(customer);
            System.out.println(customer);

            orderRepository.save(order);
            for ( CartItem cartItem: shoppingCartService.getAllItems()) {
              Product product=  productService.findById(cartItem.getProductId());
              OrderDetails orderDetails =new OrderDetails();
              orderDetails.setOrders(order);
              orderDetails.setProduct(product);
              orderDetails.setQuantity(cartItem.getQuantity());
                orderDetailRepository.save(orderDetails);
            }
            shoppingCartService.clear();
        }
        return "cart/ThongBao";
    }
}
