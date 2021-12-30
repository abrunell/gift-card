package com.bnb.giftcard.service.customer.impl;

import com.bnb.giftcard.exception.IllegalFieldValuesException;
import com.bnb.giftcard.model.Customer;
import com.bnb.giftcard.repository.CustomerRepository;
import com.bnb.giftcard.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Collection<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(String phoneNumber) {
        return customerRepository.findByphoneNumber(phoneNumber);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        if (customer.getPhoneNumber().length() != 10) {
            throw new IllegalFieldValuesException("Phone Number must contain exactly 10 digits.");
        }
        customerRepository.save(customer);
        return customer;
    }
}