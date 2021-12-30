package com.bnb.giftcard.repository;

import com.bnb.giftcard.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
