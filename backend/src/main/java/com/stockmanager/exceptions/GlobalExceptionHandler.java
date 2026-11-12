package com.stockmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalExceptionHandler {


    @ExceptionHandler(DuplicateSkuException.class)
    public ResponseEntity<String> exceptionHandler( DuplicateSkuException exception) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(exception.getMessage());

    }
    
}
