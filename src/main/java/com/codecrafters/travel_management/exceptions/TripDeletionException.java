package com.codecrafters.travel_management.exceptions;

public class TripDeletionException extends RuntimeException {
    public TripDeletionException(String message, Throwable cause) {
        super(message, cause);
    }
}
