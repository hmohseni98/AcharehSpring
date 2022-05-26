package com.spring.achareh.exceptionHandler.customException;

public class AccountNotActiveException extends RuntimeException{
    public AccountNotActiveException() {
    }

    public AccountNotActiveException(String message) {
        super(message);
    }

    public AccountNotActiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountNotActiveException(Throwable cause) {
        super(cause);
    }

    public AccountNotActiveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
