package com.um.main.exceptions;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException(Long id) {
        super("City " + id + " not found");
    }
}
