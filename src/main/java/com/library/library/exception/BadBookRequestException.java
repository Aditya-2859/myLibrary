package com.library.library.exception;

public class BadBookRequestException extends RuntimeException{
    public BadBookRequestException(String message) {
        super(message);
    }
}
