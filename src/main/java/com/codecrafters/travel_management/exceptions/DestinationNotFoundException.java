package com.codecrafters.travel_management.exceptions;

public class DestinationNotFoundException extends RuntimeException {
    public DestinationNotFoundException(Long id) {
        super("Destination not found with id: " + id);
    }
}
