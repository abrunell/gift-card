package com.bnb.giftcard.dataInitializer;

import com.bnb.giftcard.model.Customer;
import com.bnb.giftcard.model.GiftCard;
import com.bnb.giftcard.model.Purchase;
import com.bnb.giftcard.service.customer.CustomerService;
import com.bnb.giftcard.service.giftCard.GiftCardService;
import com.bnb.giftcard.service.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

// This class is only needed for testing. It's a convenient way to populate our database with some basic data.
@Component
public class DataInitializer implements CommandLineRunner {

    private final GiftCardService giftCardService;
    private final PurchaseService purchaseService;
    private final CustomerService customerService;

    @Autowired
    public DataInitializer(GiftCardService giftCardService, PurchaseService purchaseService, CustomerService customerService) {
        this.giftCardService = giftCardService;
        this.purchaseService = purchaseService;
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) {

        // Initial gift cards:
        GiftCard giftCard1 = new GiftCard();
        giftCard1.setCardNumber(1234);
        giftCard1.setInitialBalance(BigDecimal.valueOf(30));
        giftCardService.addGiftCard(giftCard1);

        GiftCard giftCard2 = new GiftCard();
        giftCard2.setCardNumber(1235);
        giftCard2.setInitialBalance(BigDecimal.valueOf(30));
        giftCardService.addGiftCard(giftCard2);

        // Initial purchases:
        Purchase purchase1 = new Purchase();
        purchase1.setAmount(BigDecimal.valueOf(5));
        purchase1.setCardNumber(1234);
        purchaseService.addPurchase(purchase1);

        Purchase purchase2 = new Purchase();
        purchase2.setAmount(BigDecimal.valueOf(5));
        purchase2.setCardNumber(1234);
        purchaseService.addPurchase(purchase2);

        // Initial Customers:
        Customer customer1 = new Customer();
        customer1.setPhoneNumber("1234567890");
        customer1.setFirstName("Abraham");
        customer1.setLastName("Lincoln");
        customerService.addCustomer(customer1);

        // Initial Registrations:
        giftCardService.updatePhoneNumber(1234L, "1234567890");
    }
}