package com.air.companies.management.system.myexceptionhandler;

import com.air.companies.management.system.exception.MyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {
    Logger logger = LogManager.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(MyException.class)
    public ResponseEntity<String> emptySetOfDriverException(MyException e) {
        logger.warn(e.getMessage());
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}