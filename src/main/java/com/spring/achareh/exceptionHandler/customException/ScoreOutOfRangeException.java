package com.spring.achareh.exceptionHandler.customException;

public class ScoreOutOfRangeException extends RuntimeException{
    public ScoreOutOfRangeException() {
    }

    public ScoreOutOfRangeException(String message) {
        super(message);
    }

    public ScoreOutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScoreOutOfRangeException(Throwable cause) {
        super(cause);
    }

    public ScoreOutOfRangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
