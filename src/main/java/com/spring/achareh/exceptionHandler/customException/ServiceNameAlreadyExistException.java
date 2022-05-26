package com.spring.achareh.exceptionHandler.customException;

public class ServiceNameAlreadyExistException extends  RuntimeException{
    public ServiceNameAlreadyExistException() {
    }

    public ServiceNameAlreadyExistException(String message) {
        super(message);
    }

    public ServiceNameAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceNameAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public ServiceNameAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
