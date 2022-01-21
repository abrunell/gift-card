package com.bnb.giftcard.service.impl;

import com.bnb.giftcard.exception.CardNotFoundException;
import com.bnb.giftcard.exception.PhoneNotFoundException;
import com.bnb.giftcard.model.Customer;
import com.bnb.giftcard.model.GiftCard;
import com.bnb.giftcard.repository.GiftCardRepository;
import com.bnb.giftcard.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GiftCardServiceImplTest {

    @Mock
    private GiftCardRepository giftCardRepository;
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private GiftCardServiceImpl giftCardServiceImpl;

    String phoneNumber = "1234567890";
    long cardNumber = 111222333444L;

    @Test
    void getGiftCards() {
        giftCardServiceImpl.getGiftCards();

        verify(giftCardRepository).findAll();
    }

    @Test
    void getActiveGiftCards() {
        giftCardServiceImpl.getActiveGiftCards();

        verify(giftCardRepository).findByActiveTrue();
    }

    @Test
    void updateGiftCard() {
        GiftCard giftCard = new GiftCard();
        giftCardServiceImpl.updateGiftCard(giftCard);

        verify(giftCardRepository).save(giftCard);
    }

    @Test
    void findByCardNumber() {
        giftCardServiceImpl.findByCardNumber(cardNumber);

        verify(giftCardRepository).findByCardNumber(cardNumber);
    }

    @Test
    void addGiftCardSuccess() {
        GiftCard giftCard = new GiftCard();
        giftCard.setInitialBalance(new BigDecimal("50.00"));

        giftCardServiceImpl.addGiftCard(giftCard);

        //remainingBalance gets set to same value as initialBalance.
        assertEquals(giftCard.getRemainingBalance(), new BigDecimal("50.00"));
        //activationTime gets set.
        assertNotNull(giftCard.getActivationTime());

        verify(giftCardRepository).save(giftCard);
    }

    //TODO: finish these tests

    @Test
    void setCustomerSuccess() {
        Set<GiftCard> emptySet = new HashSet<>();
        Customer customer = new Customer();
        customer.setGiftCards(emptySet);
        GiftCard giftCard = new GiftCard();

        when(giftCardRepository.findByCardNumber(cardNumber)).thenReturn(giftCard);
        when(customerService.getCustomer(phoneNumber)).thenReturn(customer);

        giftCardServiceImpl.setCustomer(cardNumber, phoneNumber);

        assert(giftCard.getCustomer().equals(customer));
        verify(customerService).updateCustomer(customer);
    }

    @Test
    void setCustomerNullCardThrowsException() {

        when(giftCardRepository.findByCardNumber(cardNumber)).thenReturn(null);

        Exception exception = assertThrows(CardNotFoundException.class,
                () -> giftCardServiceImpl.setCustomer(cardNumber, phoneNumber));

        assertEquals("Card Not Found.", exception.getMessage());
    }

    @Test
    void setCustomerAlreadyRegistered() {
        // Skipping this test since I'll be adding re-registration logic soon.
    }

    @Test
    void setCustomerNullCustomerThrowsException() {
        GiftCard giftCard = new GiftCard();

        when(giftCardRepository.findByCardNumber(cardNumber)).thenReturn(giftCard);
        when(customerService.getCustomer(phoneNumber)).thenReturn(null);

        Exception exception = assertThrows(PhoneNotFoundException.class,
                () -> giftCardServiceImpl.setCustomer(cardNumber, phoneNumber));

        assertEquals("No customer was found for the entered Phone Number", exception.getMessage());
    }

    @Test
    void deactivateGiftCardSuccess() {
        GiftCard giftCard = new GiftCard();
        giftCard.setActive(true);

        when(giftCardRepository.findByCardNumber(cardNumber)).thenReturn(giftCard);

        giftCardServiceImpl.deactivateGiftCard(cardNumber);

        verify(giftCardRepository).save(giftCard);
        assert(!giftCard.isActive());
    }

    @Test
    void deactivateGiftCardThrowsExceptionWhenCardNotFound() {

        when(giftCardRepository.findByCardNumber(cardNumber)).thenReturn(null);

        Exception exception = assertThrows(CardNotFoundException.class,
                () -> giftCardServiceImpl.deactivateGiftCard(cardNumber));

        assertEquals("Could not deactivate card. No card was found with the given card number.",
                exception.getMessage());
    }
}