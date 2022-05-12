package com.mindfire.config;

import org.springframework.http.HttpStatus;
/**
 * Global error handler for HTTP ERROR
 * @author sibananda
 *
 */
public class CustomHttpErrorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus; 

    public CustomHttpErrorException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
