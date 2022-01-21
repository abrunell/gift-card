package com.bnb.giftcard.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {
    
    Customer customer;

    @BeforeEach
    void setup() {
        customer = new Customer();
    }
    

    @Test
    void validationErrorWhenPhoneNumberBlank() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        assert(violations.size() > 0);
    }

    @Test
    void setPhoneNumberSuccess() {
        customer.setPhoneNumber("123--//wfwz456adf789.0");

        assertEquals(customer.getPhoneNumber(), "1234567890");
    }

    @Test
    void associateGiftCardWithCustomer() {
        GiftCard giftCard = new GiftCard();
        Set<GiftCard> emptySet = new HashSet<>();
        customer.setGiftCards(emptySet);

        customer.associateGiftCardWithCustomer(giftCard);

        assert(!customer.getGiftCards().isEmpty());
        assert(giftCard.getCustomer().equals(customer));
    }

    @Test
    void testToString() {
        String expected = "Customer{id=0, phoneNumber='null', firstName='null', lastName='null'}";
        assertEquals(customer.toString(), expected);
    }
}
