package com.bnb.giftcard.exception;

public class NumberAlreadyInUseException extends IllegalArgumentException{

    public NumberAlreadyInUseException(String errorMessage) {
        super(errorMessage);
    }
}
