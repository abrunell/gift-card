package com.bnb.giftcard.service;

import com.bnb.giftcard.model.GiftCard;

import java.util.Collection;

public interface GiftCardService {
    Collection<GiftCard> getGiftCards();
    Collection<GiftCard> getActiveGiftCards();
    GiftCard findByCardNumber(long cardNumber);
    GiftCard addGiftCard(GiftCard giftCard);
    GiftCard setCustomer(Long cardNumber, String phoneNumber);
    void updateGiftCard(GiftCard giftCard);
    GiftCard deactivateGiftCard(long cardNumber);
}
