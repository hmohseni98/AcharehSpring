package com.spring.achareh.exceptionHandler.customException;

public class InvalidDateException extends RuntimeException{
    public InvalidDateException() {
    }

    public InvalidDateException(String message) {
        super(message);
    }

    public InvalidDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDateException(Throwable cause) {
        super(cause);
    }

    public InvalidDateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
