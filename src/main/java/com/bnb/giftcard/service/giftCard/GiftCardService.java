package com.bnb.giftcard.service.giftCard;

import com.bnb.giftcard.model.GiftCard;

import java.util.Collection;

public interface GiftCardService {
    Collection<GiftCard> getGiftCards();
    GiftCard findByCardNumber(long cardNumber);
    GiftCard addGiftCard(GiftCard giftCard);
    GiftCard updatePhoneNumber(Long cardNumber, String phoneNumber);
    GiftCard updateGiftCard(GiftCard giftCard);
}
