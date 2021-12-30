package com.bnb.giftcard.service.purchase.impl;

import com.bnb.giftcard.exception.IllegalFieldValuesException;
import com.bnb.giftcard.model.GiftCard;
import com.bnb.giftcard.model.Purchase;
import com.bnb.giftcard.repository.PurchaseRepository;
import com.bnb.giftcard.service.giftCard.GiftCardService;
import com.bnb.giftcard.service.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final GiftCardService giftCardService;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, GiftCardService giftCardService) {
        this.purchaseRepository = purchaseRepository;
        this.giftCardService = giftCardService;
    }

    @Override
    public Collection<Purchase> getPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase addPurchase(Purchase purchase) {
        purchase.setDateTime(LocalDateTime.now());
        GiftCard giftCard = giftCardService.findByCardNumber(purchase.getCardNumber());
        if (giftCard == null) {
            throw new IllegalFieldValuesException("Card Not Found. No puchase has been recorded.");
        }

        giftCard.associatePurchaseWithGiftCard(purchase);
        giftCard.setRemainingBalance(giftCard.getRemainingBalance().subtract(purchase.getAmount()));
        // If hibernate's "cascade" is set, this will also save the Purchase.
        giftCardService.updateGiftCard(giftCard);
        return purchase;
    }

    @Override
    public Purchase removePurchase(Purchase purchase) {
        return null;
    }
}
