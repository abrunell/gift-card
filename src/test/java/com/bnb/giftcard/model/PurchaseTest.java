package com.bnb.giftcard.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PurchaseTest {

    Purchase purchase;

    @BeforeEach
    void setup() {
        purchase = new Purchase();
    }

    @Test
    void setAmountTrimsDecimals() {
        purchase.setAmount(BigDecimal.valueOf(1.21632));

        assertEquals(purchase.getAmount(), BigDecimal.valueOf(1.21));
    }

    @Test
    void setAmountToNull() {
        purchase.setAmount(null);

        assert(purchase.getAmount() == null);
    }

    @Test
    void testToString() {
        String expected = "Purchase{id=null, purchaseDate=null, amount=null, giftCard=null}";
        assertEquals(expected, purchase.toString());
    }
}