package com.bnb.giftcard.controller;

import com.bnb.giftcard.exception.CardNotFoundException;
import com.bnb.giftcard.exception.IllegalFieldValuesException;
import com.bnb.giftcard.exception.PhoneNotFoundException;
import com.bnb.giftcard.model.GiftCard;
import com.bnb.giftcard.service.GiftCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final GiftCardService giftCardService;

    @Autowired
    public RegistrationController(GiftCardService giftCardService) {
        this.giftCardService = giftCardService;
    }

    @GetMapping("")
    public String registrationForm (Model model, @RequestParam(required=false) Long cardNumber) {
        model.addAttribute("cardNumber", cardNumber);
        return "registration";
    }

    @PostMapping("")
    public RedirectView registerCard(@RequestParam long cardNumber, @RequestParam String phoneNumber, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/giftCard");
        GiftCard registeredGiftCard = giftCardService.setCustomer(cardNumber, phoneNumber);
        redirectAttributes.addFlashAttribute("registrationSuccess", true);
        redirectAttributes.addFlashAttribute("registeredGiftCard", registeredGiftCard);
        redirectAttributes.addFlashAttribute("giftCard", new GiftCard());
        redirectAttributes.addFlashAttribute("giftCardList", giftCardService.getActiveGiftCards());
        return redirectView;
    }

    @ExceptionHandler(CardNotFoundException.class)
    public RedirectView handleCardNotFoundException(CardNotFoundException exception, RedirectAttributes redirectAttributes, HttpServletRequest req) {
        //TODO: This seems like the wrong way to pass the cardNumber back to the registration page; is there a better way?
        final RedirectView redirectView = new RedirectView("/registration?cardNumber=" + req.getParameter("cardNumber"), true);
        redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        redirectAttributes.addFlashAttribute("exceptionThrown", true);
        return redirectView;
    }

    @ExceptionHandler(PhoneNotFoundException.class)
    public RedirectView handlePhoneNotFoundException(PhoneNotFoundException exception, RedirectAttributes redirectAttributes, HttpServletRequest req) {
        //TODO: This seems like the wrong way to pass the cardNumber back to the registration page; is there a better way?
        final RedirectView redirectView = new RedirectView("/registration?cardNumber=" + req.getParameter("cardNumber"), true);
        redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        redirectAttributes.addFlashAttribute("exceptionThrown", true);
        return redirectView;
    }

    @ExceptionHandler(IllegalFieldValuesException.class)
    public RedirectView handleIllegalFieldValuesException(IllegalFieldValuesException exception, RedirectAttributes redirectAttributes, HttpServletRequest req) {
        //TODO: This seems like the wrong way to pass the cardNumber back to the registration page; is there a better way?
        final RedirectView redirectView = new RedirectView("/registration?cardNumber=" + req.getParameter("cardNumber"), true);
        redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        redirectAttributes.addFlashAttribute("exceptionThrown", true);
        return redirectView;
    }
}
