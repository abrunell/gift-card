package com.bnb.giftcard.model;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GiftCardTest {

    @Test
    void associatePurchaseWithGiftCardSuccess() throws NoSuchFieldException, IllegalAccessException {

        //We need a GiftCard with a "purchases" set that is empty, but not null:
        GiftCard giftCard = new GiftCard();
        Field purchasesField = GiftCard.class.getDeclaredField("purchases");
        purchasesField.setAccessible(true);
        Set<Purchase> purchaseSet = new HashSet<Purchase>();
        purchasesField.set(giftCard, purchaseSet);

        //Create a Purchase and associate it with our GiftCard:
        Purchase purchase = new Purchase();
        giftCard.associatePurchaseWithGiftCard(purchase);

        //Our GiftCard now has an attached Purchase, and vice versa
        assertNotNull(giftCard.getPurchases().iterator().next());
        assertNotNull(purchase.getGiftCard());
    }

    @Test
    void validationErrorWhenSettingInitialBalanceAbove50() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        GiftCard giftCard = new GiftCard();
        giftCard.setInitialBalance(new BigDecimal("50.01"));
        Set<ConstraintViolation<GiftCard>> violations = validator.validate(giftCard);

        assert(violations.size() > 0);
    }

    @Test
    void exceptionThrownWhenBalanceOver50() {

    }
}