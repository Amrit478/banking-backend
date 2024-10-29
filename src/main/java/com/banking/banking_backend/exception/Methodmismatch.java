package com.banking.banking_backend.exception;

public class Methodmismatch extends RuntimeException {
        public Methodmismatch(String field, String expectedType) {
            super("Invalid data type for field '" + field + "'. Expected type: " + expectedType + ".");
        }
    }
