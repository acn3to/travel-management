package com.codecrafters.travel_management.exceptions;

public class TripUpdateException extends RuntimeException {
    public TripUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
