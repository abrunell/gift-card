package com.bnb.giftcard.service.purchase;

import com.bnb.giftcard.model.Purchase;

import java.math.BigDecimal;
import java.util.Collection;

public interface PurchaseService {
    Collection<Purchase> getPurchases();
    Purchase addPurchase(BigDecimal amount, long cardNumber);
}
