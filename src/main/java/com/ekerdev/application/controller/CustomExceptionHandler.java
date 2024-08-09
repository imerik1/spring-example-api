package com.ekerdev.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ekerdev.application.exception.ErrorMessage;
import com.ekerdev.application.exception.UserNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ UserNotFoundException.class })
    public ResponseEntity<ErrorMessage> handleUserNotFound(UserNotFoundException exception) {
        return ResponseEntity.status(exception.getStatus())
                .body(new ErrorMessage(exception.getMessage(), exception.getStatus()));
    }
}
