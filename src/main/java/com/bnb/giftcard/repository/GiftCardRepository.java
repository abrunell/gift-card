package com.bnb.giftcard.repository;

import com.bnb.giftcard.model.GiftCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface GiftCardRepository extends JpaRepository<GiftCard, Long> {

    GiftCard findByCardNumber(long cardNumber);
    Collection<GiftCard> findByActiveTrue();

}
