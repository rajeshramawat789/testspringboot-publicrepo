package com.test.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.exception.ApiException;
import com.test.exception.ErrorResource;
import com.test.exception.FieldErrorResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResource handle(MethodArgumentNotValidException exception) {

        List<FieldErrorResource> collect = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(
                        fieldError -> new FieldErrorResource(fieldError.getObjectName(),
                                fieldError.getField(),
                                fieldError.getDefaultMessage(),
                                fieldError.getRejectedValue())
                ).collect(Collectors.toList());
        ErrorResource errorResource = new ErrorResource(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "One of the values provided is not supported or has an invalid format");
        errorResource.setFieldErrors(collect);

        return errorResource;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResource handle(ValidationException exception) {
        LOGGER.error(exception.getMessage(), exception);
        return new ErrorResource(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResource handle(MethodArgumentTypeMismatchException exception) {
        String message = "Passed parameter " + exception.getName() + " is invalid.";
        LOGGER.error(message, exception);
        return new ErrorResource(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), message);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResource handle(JsonProcessingException exception) {
        LOGGER.error(exception.getMessage(), exception);
        return new ErrorResource(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), exception.getOriginalMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResource handle(ApiException exception) {
        LOGGER.error(exception.getMessage(), exception);
        return new ErrorResource(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    protected ErrorResource handle(Exception ex, HttpServletRequest request) {
        LOGGER.error("An error has occurred on {}", request.getRequestURI(), ex);
        ErrorResource errorResource = new ErrorResource(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal error. Please report to IT Team.", HttpStatus.INTERNAL_SERVER_ERROR.name());
        return errorResource;
    }

}
