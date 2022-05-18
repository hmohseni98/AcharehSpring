package com.spring.achareh.customException;

public class AccountNotActive extends RuntimeException{
    public AccountNotActive() {
        super("your account not active");
    }
}
