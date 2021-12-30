package com.bnb.giftcard.exception;

public class CardNotFoundException extends IllegalArgumentException{

    public CardNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

