package com.spring.achareh.customException;

public class AccessDenied extends RuntimeException{
    public AccessDenied() {
        super("access denied");
    }
}
