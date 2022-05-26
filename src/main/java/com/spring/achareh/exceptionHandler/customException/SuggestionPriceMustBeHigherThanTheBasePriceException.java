package com.spring.achareh.exceptionHandler.customException;

public class SuggestionPriceMustBeHigherThanTheBasePriceException extends RuntimeException {
    public SuggestionPriceMustBeHigherThanTheBasePriceException() {
    }

    public SuggestionPriceMustBeHigherThanTheBasePriceException(String message) {
        super(message);
    }

    public SuggestionPriceMustBeHigherThanTheBasePriceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuggestionPriceMustBeHigherThanTheBasePriceException(Throwable cause) {
        super(cause);
    }

    public SuggestionPriceMustBeHigherThanTheBasePriceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
