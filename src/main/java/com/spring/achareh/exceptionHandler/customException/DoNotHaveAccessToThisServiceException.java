package com.spring.achareh.exceptionHandler.customException;

public class DoNotHaveAccessToThisServiceException extends RuntimeException{
    public DoNotHaveAccessToThisServiceException() {
    }

    public DoNotHaveAccessToThisServiceException(String message) {
        super(message);
    }

    public DoNotHaveAccessToThisServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoNotHaveAccessToThisServiceException(Throwable cause) {
        super(cause);
    }

    public DoNotHaveAccessToThisServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
