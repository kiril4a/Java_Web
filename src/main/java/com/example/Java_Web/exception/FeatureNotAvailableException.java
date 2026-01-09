package com.example.Java_Web.exception;

public class FeatureNotAvailableException extends RuntimeException {
    public FeatureNotAvailableException(String message) {
        super(message);
    }
}
