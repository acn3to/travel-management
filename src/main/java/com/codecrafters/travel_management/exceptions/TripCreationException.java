package com.codecrafters.travel_management.exceptions;

public class TripCreationException extends RuntimeException {
    public TripCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
