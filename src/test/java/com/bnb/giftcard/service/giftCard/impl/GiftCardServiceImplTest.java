package com.bnb.giftcard.service.giftCard.impl;

import com.bnb.giftcard.exception.NumberAlreadyInUseException;
import com.bnb.giftcard.model.GiftCard;
import com.bnb.giftcard.repository.GiftCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GiftCardServiceImplTest {

    @Mock
    private GiftCardRepository giftCardRepository;
    @InjectMocks
    private GiftCardServiceImpl giftCardServiceImpl;

    @Test
    void getGiftCards() {
        giftCardServiceImpl.getGiftCards();

        // should call the findAll() method on the repo.
        verify(giftCardRepository).findAll();
    }

    @Test
    void updateGiftCard() {
        GiftCard giftCard = new GiftCard();
        giftCardServiceImpl.updateGiftCard(giftCard);

        // should call the save() method on the repo.
        verify(giftCardRepository).save(giftCard);
    }

    @Test
    void findByCardNumber() {
        long cardNumber = 1234567;
        giftCardServiceImpl.findByCardNumber(cardNumber);

        verify(giftCardRepository).findBycardNumber(cardNumber);
    }

    @Test
    void addGiftCardSuccess() {
        GiftCard giftCard = new GiftCard();
        giftCard.setCardNumber(1234567);
        giftCard.setInitialBalance(new BigDecimal("50.00"));

        giftCardServiceImpl.addGiftCard(giftCard);

        //remainingBalance gets set to same value as initialBalance.
        assertEquals(giftCard.getRemainingBalance(), new BigDecimal("50.00"));
        //activationTime gets set.
        assertNotNull(giftCard.getActivationTime());

        verify(giftCardRepository).save(giftCard);
    }

    @Test
    void exceptionThrownWhenCardNotUnique() {
        GiftCard giftCard2 = new GiftCard();
        giftCard2.setCardNumber(1234567);
        giftCard2.setInitialBalance(new BigDecimal("50.00"));

        when(giftCardRepository.findBycardNumber(1234567)).thenReturn(new GiftCard());

        NumberAlreadyInUseException exception = assertThrows(NumberAlreadyInUseException.class, () -> {
            giftCardServiceImpl.addGiftCard(giftCard2);
        });

        assertEquals("Card number already in use.", exception.getMessage());
    }

    //TODO: finish these tests

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
}