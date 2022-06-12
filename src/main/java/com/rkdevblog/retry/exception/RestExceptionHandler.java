package com.rkdevblog.retry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BackendException.class)
    public ResponseEntity<String> handleBackEndException(BackendException backendException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(backendException.getMessage());
    }
}
