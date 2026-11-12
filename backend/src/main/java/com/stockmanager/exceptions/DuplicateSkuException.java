package com.stockmanager.exceptions;

public class DuplicateSkuException extends RuntimeException {


    public DuplicateSkuException (String message) {
        super(message);
    }
    
}
