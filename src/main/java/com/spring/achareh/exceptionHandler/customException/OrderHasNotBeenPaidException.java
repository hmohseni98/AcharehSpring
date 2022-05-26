package com.spring.achareh.exceptionHandler.customException;

public class OrderHasNotBeenPaidException extends RuntimeException{
    public OrderHasNotBeenPaidException() {
    }

    public OrderHasNotBeenPaidException(String message) {
        super(message);
    }

    public OrderHasNotBeenPaidException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderHasNotBeenPaidException(Throwable cause) {
        super(cause);
    }

    public OrderHasNotBeenPaidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
