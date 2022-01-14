package com.bnb.giftcard.controller.customer;

import com.bnb.giftcard.exception.IllegalFieldValuesException;
import com.bnb.giftcard.model.Customer;
import com.bnb.giftcard.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/viewCustomers")
    public String viewCustomers(Model model) {
        model.addAttribute("customers", customerService.getCustomers());
        return "view-customers";
    }

    @GetMapping("/addCustomer")
    public String addCustomerView(Model model) {
        model.addAttribute("customer", new Customer());
        return "add-customer";
    }

    @PostMapping("/addCustomer")
    public ModelAndView addCustomer(@ModelAttribute("customer") Customer customer, BindingResult result, ModelAndView modelAndView) {
        modelAndView.setViewName("add-customer");
        if (result.hasErrors()) {
            return modelAndView;
        }
        Customer savedCustomer = customerService.addCustomer(customer);
        modelAndView.addObject("addCustomerSuccess", true);
        modelAndView.addObject("savedCustomer", savedCustomer);
        return modelAndView;
    }

    @ExceptionHandler(IllegalFieldValuesException.class)
    public RedirectView handleIllegalFieldValuesException(IllegalFieldValuesException exception, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/customer/addCustomer", true);
        redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        redirectAttributes.addFlashAttribute("exceptionThrown", true);
        return redirectView;
    }
}
