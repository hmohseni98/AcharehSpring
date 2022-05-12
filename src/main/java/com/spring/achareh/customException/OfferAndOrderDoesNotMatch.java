package com.spring.achareh.customException;

public class OfferAndOrderDoesNotMatch extends RuntimeException{
    public OfferAndOrderDoesNotMatch() {
        super("offer and order does not match");
    }
}
