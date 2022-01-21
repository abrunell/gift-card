package com.bnb.giftcard.model;

import com.bnb.giftcard.exception.IllegalFieldValuesException;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GiftCardTest {

    @Test
    void setInitialBalanceTrimsDecimals() {
        GiftCard giftCard = new GiftCard();
        giftCard.setInitialBalance(BigDecimal.valueOf(1.21343858));

        assert(giftCard.getInitialBalance().equals(BigDecimal.valueOf(1.21)));
    }

    @Test
    void setInitialBalanceToNull() {
        GiftCard giftCard = new GiftCard();
        giftCard.setInitialBalance(null);

        assert(giftCard.getInitialBalance() == null);
    }

    @Test
    void setRemainingBalanceSuccess() {
        GiftCard giftCard = new GiftCard();
        giftCard.setRemainingBalance(BigDecimal.valueOf(0.00));

        assert(giftCard.getRemainingBalance().equals(BigDecimal.valueOf(0.00)));
    }

    @Test
    void exceptionThrownWhenSettingBalanceBelowZero() {
        GiftCard giftCard = new GiftCard();

        Exception exception = assertThrows(IllegalFieldValuesException.class,
                () -> giftCard.setRemainingBalance(BigDecimal.valueOf(-0.01)));

        assertEquals("Cannot process purchase: The selected gift card does not have a high enough balance for " +
                "this purchase", exception.getMessage());
    }

    @Test
    void associatePurchaseWithGiftCardSuccess() throws NoSuchFieldException, IllegalAccessException {

        //We need a GiftCard with a "purchases" set that is empty, but not null:
        GiftCard giftCard = new GiftCard();
        Field purchasesField = GiftCard.class.getDeclaredField("purchases");
        purchasesField.setAccessible(true);
        Set<Purchase> purchaseSet = new HashSet<>();
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
    void validatonErrorWhenSettingInitialBalanceBelowZero() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        GiftCard giftCard = new GiftCard();
        giftCard.setInitialBalance(new BigDecimal("-1.00"));
        Set<ConstraintViolation<GiftCard>> violations = validator.validate(giftCard);

        assert(violations.size() > 0);
    }

    @Test
    void validationErrorWhenInitialValueIsNull() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        GiftCard giftCard = new GiftCard();
        Set<ConstraintViolation<GiftCard>> violations = validator.validate(giftCard);

        assert(violations.size() > 0);
    }

    @Test
    void testToString() {
        GiftCard giftCard = new GiftCard();
        String expected = "GiftCard{id=null, cardNumber=0, initialBalance=null, remainingBalance=null, " +
                "activationTime=null, active=false, customer=null}";
        assertEquals(expected, giftCard.toString());
    }
}