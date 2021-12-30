package com.bnb.giftcard.controller.giftCard;

import com.bnb.giftcard.exception.IllegalFieldValuesException;
import com.bnb.giftcard.exception.NumberAlreadyInUseException;
import com.bnb.giftcard.model.GiftCard;
import com.bnb.giftcard.service.giftCard.GiftCardService;
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
@RequestMapping("/giftCard")
public class GiftCardController {

    private final GiftCardService giftCardService;

    @Autowired
    public GiftCardController(GiftCardService giftCardService) {
        this.giftCardService = giftCardService;
    }

    @GetMapping("/viewGiftCards")
    public String viewGiftCards(Model model) {
        model.addAttribute("giftCards", giftCardService.getGiftCards());
        return "view-giftCards";
    }

    @GetMapping("/addGiftCard")
    public String addGiftCardView(Model model) {
        model.addAttribute("giftCard", new GiftCard());
        return "add-giftCard";
    }

    @PostMapping("/addGiftCard")
    public ModelAndView addGiftCard(@ModelAttribute("giftCard") @Valid GiftCard giftCard, BindingResult result, ModelAndView modelAndView) {
        modelAndView.setViewName("add-giftCard");
        if (result.hasErrors()) {
            return modelAndView;
        }
        GiftCard savedGiftCard = giftCardService.addGiftCard(giftCard);
        modelAndView.addObject("addGiftCardSuccess", true);
        modelAndView.addObject("savedGiftCard", savedGiftCard);
        return modelAndView;
    }

    @ExceptionHandler(IllegalFieldValuesException.class)
    public RedirectView handleIllegalFieldValuesException(IllegalFieldValuesException exception, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/giftCard/addGiftCard", true);
        redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        redirectAttributes.addFlashAttribute("exceptionThrown", true);
        return redirectView;
    }

    @ExceptionHandler(NumberAlreadyInUseException.class)
    public RedirectView handleNumberAlreadyInUseException(NumberAlreadyInUseException exception, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/giftCard/addGiftCard", true);
        redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        redirectAttributes.addFlashAttribute("exceptionThrown", true);
        return redirectView;
    }
}
