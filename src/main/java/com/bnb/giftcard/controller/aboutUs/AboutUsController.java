package com.bnb.giftcard.controller.aboutUs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutUsController {

    @GetMapping("")
    public String welcome() {
        return "aboutUs";
    }
}
