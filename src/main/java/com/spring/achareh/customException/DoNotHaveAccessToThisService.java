package com.spring.achareh.customException;

public class DoNotHaveAccessToThisService extends RuntimeException{
    public DoNotHaveAccessToThisService() {
        super("do not have access to this service");
    }
}
