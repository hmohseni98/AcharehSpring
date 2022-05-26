package com.spring.achareh.exceptionHandler;

import com.spring.achareh.exceptionHandler.customException.*;
import com.spring.achareh.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.constraints.Email;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<String> accessDeniedExceptionHandler(AccessDeniedException ex) {
        return new ResponseEntity<String>("access denied",HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = AccountNotActiveException.class)
    public ResponseEntity<String> accountNotActiveExceptionHandler(AccountNotActiveException ex) {
        return new ResponseEntity<String>("your account not active",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CategoryDoesNotExistException.class)
    public ResponseEntity<String> categoryDoesNotExistExceptionHandler(CategoryDoesNotExistException ex) {
        return new ResponseEntity<String>("selected category does not exist",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DoNotHaveAccessToThisOrderException.class)
    public ResponseEntity<String> doNotHaveAccessToThisOrderExceptionHandler(DoNotHaveAccessToThisOrderException ex) {
        return new ResponseEntity<String>("do not have access to this order",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DoNotHaveAccessToThisServiceException.class)
    public ResponseEntity<String> DoNotHaveAccessToThisServiceExceptionHandler(DoNotHaveAccessToThisServiceException ex) {
        return new ResponseEntity<String>("do not have access to this service",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidDateException.class)
    public ResponseEntity<String> InvalidDateExceptionHandler(InvalidDateException ex) {
        return new ResponseEntity<String>("invalid date",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OldPasswordIsIncorrectException.class)
    public ResponseEntity<String> OldPasswordIsIncorrectExceptionHandler(OldPasswordIsIncorrectException ex) {
        return new ResponseEntity<String>("old password is incorrect",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OrderHasNotBeenPaidException.class)
    public ResponseEntity<String> OrderHasNotBeenPaidExceptionHandler(OrderHasNotBeenPaidException ex) {
        return new ResponseEntity<String>("order has not been paid",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ScoreOutOfRangeException.class)
    public ResponseEntity<String> ScoreOutOfRangeExceptionHandler(ScoreOutOfRangeException ex) {
        return new ResponseEntity<String>("score out of range",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ServiceNameAlreadyExistException.class)
    public ResponseEntity<String> ServiceNameAlreadyExistExceptionHandler(ServiceNameAlreadyExistException ex) {
        return new ResponseEntity<String>("service name already exist",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SuggestionPriceMustBeHigherThanTheBasePriceException.class)
    public ResponseEntity<String> SuggestionPriceMustBeHigherThanTheBasePriceExceptionHandler(SuggestionPriceMustBeHigherThanTheBasePriceException ex) {
        return new ResponseEntity<String>("suggestion price must be higher than the base price",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailAlreadyExistException.class)
    public ResponseEntity<String> EmailAlreadyExistExceptionHandler(EmailAlreadyExistException ex) {
        return new ResponseEntity<String>("this email already exist",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> allHandler(RuntimeException ex) {
        return new ResponseEntity<String>("unknown error, please try again later!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
