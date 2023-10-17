package com.um.main.exceptions;

public class BusNotFoundException extends RuntimeException{
    public BusNotFoundException(Long id) {
        super("Bus " + id + " not found");
    }
}
