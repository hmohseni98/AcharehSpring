package com.spring.achareh.exceptionHandler.customException;

public class UsernameOrPasswordInCorrectException extends RuntimeException{
    public UsernameOrPasswordInCorrectException() {
    }

    public UsernameOrPasswordInCorrectException(String message) {
        super(message);
    }

    public UsernameOrPasswordInCorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameOrPasswordInCorrectException(Throwable cause) {
        super(cause);
    }

    public UsernameOrPasswordInCorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
