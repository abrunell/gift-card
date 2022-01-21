package com.bnb.giftcard.service.giftCard.impl;

import com.bnb.giftcard.model.Customer;
import com.bnb.giftcard.model.GiftCard;
import com.bnb.giftcard.repository.GiftCardRepository;
import com.bnb.giftcard.service.customer.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @Test
    void getGiftCards() {
        giftCardServiceImpl.getGiftCards();

        // should call the findAll() method on the repo.
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
        long cardNumber = 1234567;
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
        long cardNumber = 112233445566L;
        String phoneNumber = "1234567890";
        //Need to add an empty GiftCard list to this? But why isn't that a problem in the real world?
        Customer customer = new Customer();

        GiftCard giftCard = new GiftCard();

        when(giftCardRepository.findByCardNumber(cardNumber)).thenReturn(giftCard);
        when(customerService.getCustomer(phoneNumber)).thenReturn(customer);

        giftCardServiceImpl.setCustomer(cardNumber, phoneNumber);


        verify(customer).associateGiftCardWithCustomer(giftCard);
        verify(customerService).updateCustomer(customer);
    }

    @Test
    void updatePhoneNumberSuccess() {
        //phone number is set
        //save method gets called
    }

    @Test
    void exceptionWhenNoCardFound() {

    }

    @Test
    void exceptionWhenPhoneNotFound() {

    }

    // 8772244430
}