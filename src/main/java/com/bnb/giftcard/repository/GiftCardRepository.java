package com.bnb.giftcard.repository;

import com.bnb.giftcard.model.GiftCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftCardRepository extends JpaRepository<GiftCard, Long> {

    GiftCard findBycardNumber(long cardNumber);

}
