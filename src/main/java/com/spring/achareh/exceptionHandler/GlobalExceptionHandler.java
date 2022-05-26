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
    public ResponseEntity<String> accessDeniedExceptionHandler() {
        logger.error("access denied");
        return new ResponseEntity<>("access denied", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = AccountNotActiveException.class)
    public ResponseEntity<String> accountNotActiveExceptionHandler() {
        logger.error("account not active");
        return new ResponseEntity<>("account not active", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CategoryDoesNotExistException.class)
    public ResponseEntity<String> categoryDoesNotExistExceptionHandler() {
        logger.error("selected category does not exist");
        return new ResponseEntity<>("selected category does not exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DoNotHaveAccessToThisOrderException.class)
    public ResponseEntity<String> doNotHaveAccessToThisOrderExceptionHandler() {
        logger.error("do not have access to this order");
        return new ResponseEntity<>("do not have access to this order", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DoNotHaveAccessToThisServiceException.class)
    public ResponseEntity<String> DoNotHaveAccessToThisServiceExceptionHandler() {
        logger.error("do not have access to this service");
        return new ResponseEntity<>("do not have access to this service", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidDateException.class)
    public ResponseEntity<String> InvalidDateExceptionHandler() {
        logger.error("invalid date");
        return new ResponseEntity<>("invalid date", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OldPasswordIsIncorrectException.class)
    public ResponseEntity<String> OldPasswordIsIncorrectExceptionHandler() {
        logger.error("old password is incorrect");
        return new ResponseEntity<>("old password is incorrect", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OrderHasNotBeenPaidException.class)
    public ResponseEntity<String> OrderHasNotBeenPaidExceptionHandler() {
        logger.error("order has not been paid");
        return new ResponseEntity<>("order has not been paid", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ScoreOutOfRangeException.class)
    public ResponseEntity<String> ScoreOutOfRangeExceptionHandler() {
        logger.error("score out of range");
        return new ResponseEntity<>("score out of range", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ServiceNameAlreadyExistException.class)
    public ResponseEntity<String> ServiceNameAlreadyExistExceptionHandler() {
        logger.error("service name already exist");
        return new ResponseEntity<>("service name already exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SuggestionPriceMustBeHigherThanTheBasePriceException.class)
    public ResponseEntity<String> SuggestionPriceMustBeHigherThanTheBasePriceExceptionHandler() {
        logger.error("suggestion price must be higher than the base price");
        return new ResponseEntity<>("suggestion price must be higher than the base price", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailAlreadyExistException.class)
    public ResponseEntity<String> EmailAlreadyExistExceptionHandler() {
        logger.error("email already exist");
        return new ResponseEntity<>("email already exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameOrPasswordInCorrectException.class)
    public ResponseEntity<String> UsernameOrPasswordInCorrectExceptionHandler() {
        logger.error("username or password in correct");
        return new ResponseEntity<>("username or password in correct", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> allHandler(RuntimeException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>("unknown error, please try again later!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
