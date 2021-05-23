package com.test.exception;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
public class ApiException extends RuntimeException {

    public ApiException(String message){
        super(message);
    }

}
