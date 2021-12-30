package com.bnb.giftcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bnb.giftcard")
public class GiftCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(GiftCardApplication.class, args);
    }

}

//TODO: Replace default WhiteLabelErrorPage.
//TODO: Use a lambda somewhere (total gift card liability?)
//TODO: Return some JSON restfully somewhere.
