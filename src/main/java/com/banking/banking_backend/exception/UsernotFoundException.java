package com.banking.banking_backend.exception;

public class UsernotFoundException extends RuntimeException {
    public UsernotFoundException(String message) {
        super(message);
    }
}