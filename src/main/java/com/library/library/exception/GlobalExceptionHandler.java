package com.library.library.exception;

import com.library.library.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadBookRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadBookRequestException ex, WebRequest request) {
        ErrorResponse errorResponse = new
                ErrorResponse(ex.getMessage(), request.getDescription(false), new Date());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new
                ErrorResponse(ex.getMessage(), request.getDescription(false), new Date());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
