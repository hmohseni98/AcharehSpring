package com.spring.achareh.exceptionHandler.customException;

public class DoNotHaveAccessToThisOrderException extends RuntimeException{
    public DoNotHaveAccessToThisOrderException() {
    }

    public DoNotHaveAccessToThisOrderException(String message) {
        super(message);
    }

    public DoNotHaveAccessToThisOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoNotHaveAccessToThisOrderException(Throwable cause) {
        super(cause);
    }

    public DoNotHaveAccessToThisOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
