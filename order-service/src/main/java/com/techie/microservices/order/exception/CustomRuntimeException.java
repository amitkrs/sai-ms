package com.techie.microservices.order.exception;

import org.springframework.http.HttpStatusCode;

public class CustomRuntimeException extends RuntimeException {

    private final HttpStatusCode httpStatusCode;

    public CustomRuntimeException(HttpStatusCode httpStatusCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public CustomRuntimeException(HttpStatusCode httpStatusCode, String message, Throwable cause) {
        super(message, cause);
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }
}
