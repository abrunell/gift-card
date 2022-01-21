package com.bnb.giftcard.controller;

import com.bnb.giftcard.model.GiftCard;
import com.bnb.giftcard.service.giftCard.GiftCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping("/giftCard")
public class GiftCardController {

    private final GiftCardService giftCardService;

    @Autowired
    public GiftCardController(GiftCardService giftCardService) {
        this.giftCardService = giftCardService;
    }

    @GetMapping("")
    public String viewGiftCards(Model model) {
        model.addAttribute("giftCard", new GiftCard());
        model.addAttribute("giftCardList", giftCardService.getActiveGiftCards());
        return "giftCards";
    }

    @PostMapping("")
    public String addGiftCard(@ModelAttribute("giftCard") @Valid GiftCard giftCard, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("giftCardList", giftCardService.getActiveGiftCards());
            return "giftCards";
        }
        GiftCard savedGiftCard = giftCardService.addGiftCard(giftCard);
        model.addAttribute("giftCardList", giftCardService.getActiveGiftCards());
        model.addAttribute("addGiftCardSuccess", true);
        model.addAttribute("savedGiftCard", savedGiftCard);
        model.addAttribute("giftCard", new GiftCard());
        return "giftCards";
    }

    @PostMapping("/deactivate")
    public RedirectView deactivateGiftCard(RedirectAttributes attributes, @RequestParam long cardNumber) {
        GiftCard deactivatedGiftCard = giftCardService.deactivateGiftCard(cardNumber);
        attributes.addFlashAttribute("deactivatedGiftCard", deactivatedGiftCard);
        attributes.addFlashAttribute("deactivationSuccess", true);
        return new RedirectView("/giftCard");
    }
}
