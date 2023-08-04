package com.um.main.exceptions;

public class PassengerNotFoundException extends RuntimeException {
    public PassengerNotFoundException(Long id) {
        super("Passenger " + id + " not found");
    }
}
