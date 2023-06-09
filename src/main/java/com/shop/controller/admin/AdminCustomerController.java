package com.shop.controller.admin;

import com.shop.entities.Category;
import com.shop.entities.Comment;
import com.shop.entities.Customer;
import com.shop.entities.Product;
import com.shop.repositories.CustomerRepository;
import com.shop.repositories.ProductRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/admin/customer")
public class AdminCustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/create")
    public String create(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "admin/customer/create";
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("listCustomers", customerRepository.findAll());
        return "admin/customer/index";
    }

    @GetMapping("/delete/{idCustomer}")
    public String deleteCustomerByAdmin(@PathVariable("idCustomer") Integer idCustomer) {
        Customer customer = customerRepository.findById(idCustomer).orElseThrow(() -> new RuntimeException("Invalid customer id"));
        customerRepository.delete(customer);
        return "redirect:/admin/customer";
    }

    @GetMapping("/edit/{idCustomer}")
    public String showForm(Model model,
                           @PathVariable Integer idCustomer,
                           Principal principal,
                           RedirectAttributes redirectAttributes) {
        Customer customer = customerRepository.findById(idCustomer).orElseThrow(() -> new RuntimeException("Invalid id customer"));
        model.addAttribute("customer", customer);
        return "/admin/customer/edit";
    }

    @PostMapping("/edit/{idCustomer}")
    public String editCommentByAdmin(@PathVariable Integer idCustomer, @ModelAttribute("customer") Customer updateCustomer, Principal principal, RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        Customer customer = customerRepository.findById(idCustomer).orElseThrow(() -> new RuntimeException("Invalid customer id"));
        if (!customer.getEmail().equals(updateCustomer.getEmail()) && customerRepository.existsByEmail(updateCustomer.getEmail())) {
            bindingResult.rejectValue("email", "duplicate", "Email đã tồn tại trong database, vui lòng nhập email khác");
            return "/admin/customer/edit";
        }
        if (!customer.getPhone().equals(updateCustomer.getPhone()) && customerRepository.existsByPhone(updateCustomer.getPhone())) {
            bindingResult.rejectValue("phone", "duplicatePhone", "Số điện thoại đã tồn tại trong database, vui lòng nhập số điện thoại khác");
            return "/admin/customer/edit";
        }
        customer.setEmail(updateCustomer.getEmail());
        customer.setName(updateCustomer.getName());
        customer.setGender(updateCustomer.getGender());
        customer.setRole(updateCustomer.getRole());
        customer.setPhone(updateCustomer.getPhone());
        customerRepository.save(customer);
        return "redirect:/admin/customer";
    }


    @PostMapping("/create")
    public String createUser(@ModelAttribute("customer") Customer customer, BindingResult result) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            result.rejectValue("email", "duplicate", "Email đã tồn tại trong database, vui lòng nhập email khác");
            return "admin/customer/create";
        }
        if (customerRepository.existsByPhone(customer.getPhone())) {
            result.rejectValue("phone", "duplicate", "Số diện thọại đã tồn tại trong database, vui lòng nhập số điện thoại khác");
            return "admin/customer/create";
        }
        if (!customer.getPhone().matches("\\d+")) {
            result.rejectValue("phone", "invalid", "Số điện thoại chỉ được chứa số");
            return "admin/customer/create";
        }
        String hashedPassword = BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt());
        customer.setPassword(hashedPassword);
        customerRepository.save(customer);
        return "redirect:/admin/customer";
    }
}
