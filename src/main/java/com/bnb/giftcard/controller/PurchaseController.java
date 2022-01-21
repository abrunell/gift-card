package com.bnb.giftcard.controller;

import com.bnb.giftcard.exception.IllegalFieldValuesException;
import com.bnb.giftcard.model.Purchase;
import com.bnb.giftcard.service.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("")
    public String viewPurchases(Model model) {
        model.addAttribute("purchaseList", purchaseService.getPurchases());
        return "purchases";
    }

    @PostMapping("")
    public String addPurchase(Model model, @RequestParam long cardNumber, @RequestParam BigDecimal amount) {
        Purchase savedPurchase = purchaseService.addPurchase(amount, cardNumber);
        model.addAttribute("purchaseList", purchaseService.getPurchases());
        model.addAttribute("addPurchaseSuccess", true);
        model.addAttribute("savedPurchase", savedPurchase);
        return "purchases";
    }

    @ExceptionHandler(IllegalFieldValuesException.class)
    public String handleIllegalFieldValuesException(IllegalFieldValuesException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        model.addAttribute("exceptionThrown", true);
        model.addAttribute("purchaseList", purchaseService.getPurchases());
        return "purchases";
    }


}

