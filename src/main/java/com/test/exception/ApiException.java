package com.test.exception;

/**
 * Custom exceptions
 */
public class ApiException extends RuntimeException {

    public ApiException(String message){
        super(message);
    }

}
