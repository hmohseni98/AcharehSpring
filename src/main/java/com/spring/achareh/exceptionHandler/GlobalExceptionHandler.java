package com.spring.achareh.exceptionHandler;

import com.spring.achareh.exceptionHandler.customException.*;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<String> accessDeniedExceptionHandler(AccessDeniedException ex) {
        logger.error("access denied");
        return new ResponseEntity<String>("access denied",HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = AccountNotActiveException.class)
    public ResponseEntity<String> accountNotActiveExceptionHandler(AccountNotActiveException ex) {
        logger.error("account not active");
        return new ResponseEntity<String>("account not active",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CategoryDoesNotExistException.class)
    public ResponseEntity<String> categoryDoesNotExistExceptionHandler(CategoryDoesNotExistException ex) {
        logger.error("selected category does not exist");
        return new ResponseEntity<String>("selected category does not exist",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DoNotHaveAccessToThisOrderException.class)
    public ResponseEntity<String> doNotHaveAccessToThisOrderExceptionHandler(DoNotHaveAccessToThisOrderException ex) {
        logger.error("do not have access to this order");
        return new ResponseEntity<String>("do not have access to this order",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DoNotHaveAccessToThisServiceException.class)
    public ResponseEntity<String> DoNotHaveAccessToThisServiceExceptionHandler(DoNotHaveAccessToThisServiceException ex) {
        logger.error("do not have access to this service");
        return new ResponseEntity<String>("do not have access to this service",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidDateException.class)
    public ResponseEntity<String> InvalidDateExceptionHandler(InvalidDateException ex) {
        logger.error("invalid date");
        return new ResponseEntity<String>("invalid date",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OldPasswordIsIncorrectException.class)
    public ResponseEntity<String> OldPasswordIsIncorrectExceptionHandler(OldPasswordIsIncorrectException ex) {
        logger.error("old password is incorrect");
        return new ResponseEntity<String>("old password is incorrect",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OrderHasNotBeenPaidException.class)
    public ResponseEntity<String> OrderHasNotBeenPaidExceptionHandler(OrderHasNotBeenPaidException ex) {
        logger.error("order has not been paid");
        return new ResponseEntity<String>("order has not been paid",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ScoreOutOfRangeException.class)
    public ResponseEntity<String> ScoreOutOfRangeExceptionHandler(ScoreOutOfRangeException ex) {
        logger.error("score out of range");
        return new ResponseEntity<String>("score out of range",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ServiceNameAlreadyExistException.class)
    public ResponseEntity<String> ServiceNameAlreadyExistExceptionHandler(ServiceNameAlreadyExistException ex) {
        logger.error("service name already exist");
        return new ResponseEntity<String>("service name already exist",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SuggestionPriceMustBeHigherThanTheBasePriceException.class)
    public ResponseEntity<String> SuggestionPriceMustBeHigherThanTheBasePriceExceptionHandler(SuggestionPriceMustBeHigherThanTheBasePriceException ex) {
        logger.error("suggestion price must be higher than the base price");
        return new ResponseEntity<String>("suggestion price must be higher than the base price",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailAlreadyExistException.class)
    public ResponseEntity<String> EmailAlreadyExistExceptionHandler(EmailAlreadyExistException ex) {
        logger.error("email already exist");
        return new ResponseEntity<String>("email already exist",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> allHandler(RuntimeException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<String>("unknown error, please try again later!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
