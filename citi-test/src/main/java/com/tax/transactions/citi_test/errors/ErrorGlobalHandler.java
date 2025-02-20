package com.tax.transactions.citi_test.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tax.transactions.citi_test.errors.exceptions.ResourceNotFoundException;

import utils.ResponseMapper;

@RestControllerAdvice
public class ErrorGlobalHandler {

    private static final String ERROR_MSG = "Error";
    private static final String SEPARATOR = " - ";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMapper<String>> handleException(Exception e) {
        String errorMessage = ERROR_MSG + SEPARATOR + "Internal Server Error";
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseMapper<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_MSG, e.getMessage()));
    }

    @ExceptionHandler({ IllegalArgumentException.class, NullPointerException.class })
    public ResponseEntity<ResponseMapper<String>> handleBadRequest(Exception e) {
        String errorMessage = ERROR_MSG + SEPARATOR + "Bad Request";
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseMapper<>(errorMessage, HttpStatus.BAD_REQUEST.value(), ERROR_MSG, e.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseMapper<String>> handleNotFoundException(ResourceNotFoundException e) {
        String errorMessage = ERROR_MSG + SEPARATOR + "Not Found";
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseMapper<>(errorMessage, HttpStatus.NOT_FOUND.value(), ERROR_MSG, e.getMessage()));
    }
}
