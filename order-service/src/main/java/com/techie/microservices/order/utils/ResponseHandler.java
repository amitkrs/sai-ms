package com.techie.microservices.order.utils;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateSuccessResponse(Object data, HttpStatusCode httpStatusCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Success");
        response.put("data", data);
        return ResponseEntity.status(httpStatusCode).body(response);
    }

    public static ResponseEntity<Object> generateErrorResponse(Exception ex, HttpStatusCode httpStatusCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("exception", ex.getClass().getSimpleName());
        return ResponseEntity.status(httpStatusCode).body(response);
    }

}
