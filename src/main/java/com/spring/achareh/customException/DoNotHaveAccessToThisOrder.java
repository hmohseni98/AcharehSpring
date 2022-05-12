package com.spring.achareh.customException;

public class DoNotHaveAccessToThisOrder extends RuntimeException{
    public DoNotHaveAccessToThisOrder() {
        super("do not have access to this order");
    }
}
