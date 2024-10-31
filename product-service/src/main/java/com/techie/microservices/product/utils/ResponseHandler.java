package com.techie.microservices.product.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateSuccessResponse(Object data, HttpStatus status){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Success");
        response.put("data", data);
        return ResponseEntity.status(status).body(response);
    }

    public static ResponseEntity<Object> generateErrorResponse(Exception ex, HttpStatus status){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("exception", ex.getClass().getSimpleName());
        return ResponseEntity.status(status).body(response);
    }

}
