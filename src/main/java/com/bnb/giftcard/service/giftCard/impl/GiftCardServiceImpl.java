package com.bnb.giftcard.service.giftCard.impl;

import com.bnb.giftcard.exception.CardNotFoundException;
import com.bnb.giftcard.exception.NumberAlreadyInUseException;
import com.bnb.giftcard.exception.PhoneNotFoundException;
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

    @Override
    public GiftCard findByCardNumber(long cardNumber) {
        return giftCardRepository.findBycardNumber(cardNumber);
    }

    //TODO: Give LocalDateTime a Clock instance. Makes testing easier.
    @Override
    public GiftCard addGiftCard(GiftCard giftCard) {
        if (!hasUniqueCardNumber(giftCard)) {
            throw new NumberAlreadyInUseException("Card number already in use.");
        }
        giftCard.setRemainingBalance(giftCard.getInitialBalance());
        giftCard.setActivationTime(LocalDateTime.now());
        giftCardRepository.save(giftCard);
        return giftCard;
    }

    private boolean hasUniqueCardNumber(GiftCard giftCard) {
        if (giftCardRepository.findBycardNumber(giftCard.getCardNumber()) == null) {
            return true;
        }
        return false;
    }

    public GiftCard updatePhoneNumber(Long cardNumber, String phoneNumber) {
        GiftCard giftCard = giftCardRepository.findBycardNumber(cardNumber);
        if (giftCard == null) {
            throw new CardNotFoundException("Card Not Found.");
        }
        if (customerService.getCustomer(phoneNumber) == null) {
            throw new PhoneNotFoundException("No customer was found for the entered Phone Number");
        }
        giftCard.setPhoneNumber(phoneNumber);
        giftCardRepository.save(giftCard);
        return giftCard;
    }

    @Override
    public GiftCard updateGiftCard(GiftCard giftCard) {
        giftCardRepository.save(giftCard);
        return giftCard;
    }
}

