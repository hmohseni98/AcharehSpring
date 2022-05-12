package com.spring.achareh.customException;

public class OldPasswordIsIncorrect extends RuntimeException{
    public OldPasswordIsIncorrect() {
        super("old password is incorrect");
    }
}
