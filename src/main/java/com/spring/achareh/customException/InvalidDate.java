package com.spring.achareh.customException;

public class InvalidDate extends RuntimeException{
    public InvalidDate() {
        super("invalid date");
    }
}
