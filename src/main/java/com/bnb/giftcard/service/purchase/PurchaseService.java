package com.bnb.giftcard.service.purchase;

import com.bnb.giftcard.model.Purchase;

import java.util.Collection;

public interface PurchaseService {
    Collection<Purchase> getPurchases();
    Purchase addPurchase(Purchase purchase);
}
