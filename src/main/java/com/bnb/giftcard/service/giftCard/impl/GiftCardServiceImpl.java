package com.bnb.giftcard.service.giftCard.impl;

import com.bnb.giftcard.exception.CardNotFoundException;
import com.bnb.giftcard.exception.IllegalFieldValuesException;
import com.bnb.giftcard.exception.PhoneNotFoundException;
import com.bnb.giftcard.model.Customer;
import com.bnb.giftcard.model.GiftCard;
import com.bnb.giftcard.repository.GiftCardRepository;
import com.bnb.giftcard.service.customer.CustomerService;
import com.bnb.giftcard.service.giftCard.GiftCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class GiftCardServiceImpl implements GiftCardService {

    private final GiftCardRepository giftCardRepository;
    private final CustomerService customerService;

    @Autowired
    public GiftCardServiceImpl(GiftCardRepository giftCardRepository, CustomerService customerService) {
        this.giftCardRepository = giftCardRepository;
        this.customerService = customerService;
    }

    @Override
    public Collection<GiftCard> getGiftCards() {
        return giftCardRepository.findAll();
    }

    public Collection<GiftCard> getActiveGiftCards() {
        return giftCardRepository.findByActiveTrue();
    }

    @Override
    public GiftCard findByCardNumber(long cardNumber) {
        return giftCardRepository.findByCardNumber(cardNumber);
    }

    //TODO: Give LocalDateTime a Clock instance. Makes testing easier.
    @Override
    public GiftCard addGiftCard(GiftCard giftCard) {
        giftCard.setCardNumber(generateUniqueId());
        giftCard.setActive(true);
        giftCard.setRemainingBalance(giftCard.getInitialBalance());
        giftCard.setActivationTime(LocalDateTime.now());
        giftCardRepository.save(giftCard);
        return giftCard;
    }

    public GiftCard setCustomer(Long cardNumber, String phoneNumber) {
        GiftCard giftCard = giftCardRepository.findByCardNumber(cardNumber);
        if (giftCard == null) {
            throw new CardNotFoundException("Card Not Found.");
        }
        Customer customer = customerService.getCustomer(phoneNumber);
        if (customer == null) {
            throw new PhoneNotFoundException("No customer was found for the entered Phone Number");
        }
        //TODO: Add functionality for changing a registration, instead of throwing an exception.
        if (giftCard.getCustomer() != null) {
            throw new IllegalFieldValuesException("The selected Gift Card has already been registered to a customer");
        }
        customer.associateGiftCardWithCustomer(giftCard);
        customerService.updateCustomer(customer);
        return giftCard;
    }

    @Override
    public void updateGiftCard(GiftCard giftCard) {
        giftCardRepository.save(giftCard);
    }

    @Override
    public GiftCard deactivateGiftCard(long cardNumber) {
        GiftCard giftCard = giftCardRepository.findByCardNumber(cardNumber);
        if (giftCard == null) {
            throw new CardNotFoundException("Could not deactivate card. No card was found with the given card number.");
        }
        giftCard.setActive(false);
        giftCardRepository.save(giftCard);
        return giftCard;
    }

    //12-digit-ID
    private long generateUniqueId() {
        long lowerLimit = 100000000000L;
        long upperLimit = 999999999999L;
        long randomLong = lowerLimit + (long) (Math.random() * (upperLimit - lowerLimit));
        while (true) {
            if(giftCardRepository.findByCardNumber(randomLong) == null) {
                return randomLong;
            }
        }
    }
}

