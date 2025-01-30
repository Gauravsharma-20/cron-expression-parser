package com.cronparser.exception;

public class InvalidCronExpressionException extends Exception {
    public InvalidCronExpressionException(String message) {
        // we can have our custom logic to handle this exception scenario
        // for now just throwing the exception
        super("[ERROR]: " + message);
    }
}