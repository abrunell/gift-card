package com.bnb.giftcard.controller.registration;

import com.bnb.giftcard.exception.CardNotFoundException;
import com.bnb.giftcard.exception.PhoneNotFoundException;
import com.bnb.giftcard.model.GiftCard;
import com.bnb.giftcard.service.giftCard.GiftCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final GiftCardService giftCardService;

    @Autowired
    public RegistrationController(GiftCardService giftCardService) {
        this.giftCardService = giftCardService;
    }

    @GetMapping("/registerCard")
    public String registerCardView(Model model) {
//        model.addAttribute("giftCard", new GiftCard());
        return "register-card";
    }

    @PostMapping("/registerCard")
    public ModelAndView registerCard(ModelAndView modelAndView, @RequestParam("cardNumber") Long cardNumber, @RequestParam("phoneNumber") String phoneNumber) {
        modelAndView.setViewName("register-card");
        GiftCard savedGiftCard = giftCardService.updatePhoneNumber(cardNumber, phoneNumber);
        modelAndView.addObject("registrationSuccess", true);
        modelAndView.addObject("savedGiftCard", savedGiftCard);
        return modelAndView;
    }

    @ExceptionHandler(CardNotFoundException.class)
    public RedirectView handleCardNotFoundException(CardNotFoundException exception, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/registration/registerCard", true);
        redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        redirectAttributes.addFlashAttribute("exceptionThrown", true);
        return redirectView;
    }

    @ExceptionHandler(PhoneNotFoundException.class)
    public RedirectView handlePhoneNotFoundException(PhoneNotFoundException exception, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/registration/registerCard", true);
        redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        redirectAttributes.addFlashAttribute("exceptionThrown", true);
        return redirectView;
    }
}
