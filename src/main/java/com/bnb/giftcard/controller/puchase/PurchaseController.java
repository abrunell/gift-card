package com.bnb.giftcard.controller.puchase;

import com.bnb.giftcard.exception.IllegalFieldValuesException;
import com.bnb.giftcard.model.Purchase;
import com.bnb.giftcard.service.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/viewPurchases")
    public String viewPurchases(Model model) {
        model.addAttribute("purchases", purchaseService.getPurchases());
        return "view-purchases";
    }

    @GetMapping("/addPurchase")
    public String addPurchaseView(Model model) {
        model.addAttribute("purchase", new Purchase());
        return "add-purchase";
    }

    @PostMapping("/addPurchase")
    public ModelAndView addPurchase(@ModelAttribute("purchase") @Valid Purchase purchase, BindingResult result, ModelAndView modelAndView) {
        modelAndView.setViewName("add-purchase");
        if (result.hasErrors()) {
            return modelAndView;
        }
        Purchase savedPurchase = purchaseService.addPurchase(purchase);
        modelAndView.addObject("addPurchaseSuccess", true);
        modelAndView.addObject("savedPurchase", savedPurchase);
        return modelAndView;
    }

    @ExceptionHandler(IllegalFieldValuesException.class)
    public RedirectView handleIllegalFieldValuesException(IllegalFieldValuesException exception, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/purchases/addPurchase", true);
        redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        redirectAttributes.addFlashAttribute("exceptionThrown", true);
        return redirectView;
    }
}

