package com.shop.controller;

import com.shop.entities.CartItem;
import com.shop.entities.Product;
import com.shop.repositories.ProductRepository;
import com.shop.service.ProductService;
import com.shop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ProductService productService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    ProductRepository productRepository;
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
    public String updateItem(@RequestParam("productId") Integer productId,@RequestParam("quantity")Integer quantity){
        shoppingCartService.update(productId,quantity);
        return "redirect:/cart";
    }
    @GetMapping("/checkout")
    public String checkout(){
        return "cart/checkout";
    }
}
