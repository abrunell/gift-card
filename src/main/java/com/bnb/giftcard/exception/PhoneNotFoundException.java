package com.bnb.giftcard.exception;

public class PhoneNotFoundException extends IllegalArgumentException {

    public PhoneNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
