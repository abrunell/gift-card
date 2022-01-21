package com.bnb.giftcard.controller;

import com.bnb.giftcard.exception.IllegalFieldValuesException;
import com.bnb.giftcard.model.Customer;
import com.bnb.giftcard.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public String viewCustomers(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("customerList", customerService.getCustomers());
        return "customers";
    }

    @PostMapping("")
    public String addCustomer(Model model, @Valid @ModelAttribute("customer") Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("customerList", customerService.getCustomers());
            return "customers";
        }
        Customer savedCustomer = customerService.addCustomer(customer);
        model.addAttribute("addCustomerSuccess", true);
        model.addAttribute("savedCustomer", savedCustomer);
        model.addAttribute("customerList", customerService.getCustomers());
        model.addAttribute("customer", new Customer());
        return "customers";
    }

    @ExceptionHandler(IllegalFieldValuesException.class)
    public String handleIllegalFieldValuesException(IllegalFieldValuesException exception, Model model) {
        model.addAttribute("exceptionThrown", true);
        model.addAttribute("errorMessage", exception.getMessage());
        model.addAttribute("customer", new Customer());
        model.addAttribute("customerList", customerService.getCustomers());
        return "customers";
    }
}
