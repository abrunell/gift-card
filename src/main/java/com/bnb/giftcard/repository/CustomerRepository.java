package com.bnb.giftcard.repository;

import com.bnb.giftcard.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    Customer findByPhoneNumber(String phoneNumber);

}
