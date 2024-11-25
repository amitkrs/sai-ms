package com.techie.microservices.order.controller;

import com.techie.microservices.order.exception.CustomRuntimeException;
import com.techie.microservices.order.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<Object> handleCustomRTE(CustomRuntimeException e) {
        return ResponseHandler.generateErrorResponse(e, e.getHttpStatusCode());
    }
}