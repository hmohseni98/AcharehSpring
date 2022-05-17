package com.spring.achareh.customException;

public class AccountInActive extends RuntimeException{
    public AccountInActive() {
        super("your account in active");
    }
}
