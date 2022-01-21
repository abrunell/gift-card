package com.bnb.giftcard.dataInitializer;

import com.bnb.giftcard.model.Customer;
import com.bnb.giftcard.model.GiftCard;
import com.bnb.giftcard.service.customer.CustomerService;
import com.bnb.giftcard.service.giftCard.GiftCardService;
import com.bnb.giftcard.service.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

// Remove this class for Prod. Only used to populate data for demoing.
@Component
public class LoadDatabase implements CommandLineRunner {

    private final GiftCardService giftCardService;
    private final PurchaseService purchaseService;
    private final CustomerService customerService;

    final Random random = new Random();

    @Autowired
    public LoadDatabase(GiftCardService giftCardService, PurchaseService purchaseService, CustomerService customerService) {
        this.giftCardService = giftCardService;
        this.purchaseService = purchaseService;
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) {
        createGiftCards(33);
        createTransactions();
        createCustomers(Arrays.asList("James.Gosling", "Stefani.Germanotta", "Abraham.Lincoln",
                "Salacious.Crumb", "Saul.Goodman", "Ada.Lovelace"));
        registerCards();
    }

    private void createCustomers(List<String> nameList) {
        for (String name : nameList) {
            Customer customer = new Customer();
            customer.setFirstName(name.split("\\.")[0]);
            customer.setLastName(name.split("\\.")[1]);
            customer.setPhoneNumber(randomPhoneNumber());
            customerService.addCustomer(customer);
        }
    }

    private void createGiftCards(int numberOfCards) {
        for (int i = 0; i < numberOfCards; i++) {
            GiftCard giftCard = new GiftCard();
            giftCard.setInitialBalance(randomInitialBalance());
            giftCardService.addGiftCard(giftCard);
        }
    }

    private void createTransactions() {
        Collection<GiftCard> giftCardList = giftCardService.getGiftCards();

        for (GiftCard giftCard : giftCardList) {
            if (random.nextBoolean()) {
                purchaseService.addPurchase(randomPurchaseAmount(), giftCard.getCardNumber());
                if (random.nextBoolean()) {
                    purchaseService.addPurchase(randomPurchaseAmount(), giftCard.getCardNumber());
                }
            }
        }
    }

    private void registerCards() {
        Collection<GiftCard> giftCardList = giftCardService.getGiftCards();

        for (GiftCard giftCard : giftCardList) {
            if (random.nextBoolean()) {
                giftCardService.setCustomer(giftCard.getCardNumber(), randomCustomer().getPhoneNumber());
            }
        }
    }

    private BigDecimal randomInitialBalance() {
        List<BigDecimal> valuesList = Arrays.asList(BigDecimal.valueOf(10), BigDecimal.valueOf(15),
                BigDecimal.valueOf(25), BigDecimal.valueOf(35), BigDecimal.valueOf(50));
        return valuesList.get(random.nextInt(valuesList.size()));
    }

    private BigDecimal randomPurchaseAmount() {
        List<BigDecimal> valuesList = Arrays.asList(BigDecimal.valueOf(3.25), BigDecimal.valueOf(3.99),
                BigDecimal.valueOf(4.45));
        return valuesList.get(random.nextInt(valuesList.size()));
    }

    private String randomPhoneNumber() {
        long lowerLimit = 1000000000L;
        long upperLimit = 9999999999L;
        String randomNumber = String.valueOf(lowerLimit + (long) (Math.random() * (upperLimit - lowerLimit)));
        while (true) {
            if(customerService.getCustomer(randomNumber) == null) {
                return randomNumber;
            }
        }
    }

    private Customer randomCustomer() {
        Collection<Customer> customerList = customerService.getCustomers();
        return customerList.stream().skip(random.nextInt(customerList.size())).findFirst().get();
    }
}