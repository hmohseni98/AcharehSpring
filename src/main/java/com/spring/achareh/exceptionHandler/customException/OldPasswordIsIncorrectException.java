package com.spring.achareh.exceptionHandler.customException;

public class OldPasswordIsIncorrectException extends RuntimeException{
    public OldPasswordIsIncorrectException() {
    }

    public OldPasswordIsIncorrectException(String message) {
        super(message);
    }

    public OldPasswordIsIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public OldPasswordIsIncorrectException(Throwable cause) {
        super(cause);
    }

    public OldPasswordIsIncorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
