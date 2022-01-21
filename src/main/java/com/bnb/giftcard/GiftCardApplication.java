package com.bnb.giftcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bnb.giftcard")
public class GiftCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(GiftCardApplication.class, args);
    }

}

//TODO: Tests
//TODO: Change the photo on my "card"
//TODO: Change photo on Github
//TODO: Write Github readme
//TODO: Commit and push.

//TODO: Purchases and negative values.
//TODO: Transactions should be run from a button on the GiftCard List.
//TODO: Cleanup custom exceptions.
//TODO: Have exception error messages show relevant field data.
//TODO: Make "features" list on home page into links
//TODO: Make Purchases into "gcTransactions"
//TODO: Add optional email field on customer
//TODO: What happens to purchases of deactivated cards?
//TODO: Logging of user actions?
//TODO: Replace default WhiteLabelErrorPage.
//TODO: Use a lambda somewhere (total gift card liability?)
//TODO: Return some JSON restfully somewhere.
//TODO: Do I need the "request.pageContext" when pointing to controller urls?
