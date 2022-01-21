package com.bnb.giftcard.service;

import com.bnb.giftcard.model.Customer;

import java.util.Collection;

public interface CustomerService {
    Collection<Customer> getCustomers();
    Customer getCustomer(String phoneNumber);
    Customer addCustomer(Customer customer);
    Customer updateCustomer(Customer customer);

}
