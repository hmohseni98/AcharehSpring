package com.spring.achareh.customException;

public class StatusOfThisOrderHasNotBeenPaid extends RuntimeException{
    public StatusOfThisOrderHasNotBeenPaid() {
        super("Status of this order has not been paid");
    }
}
