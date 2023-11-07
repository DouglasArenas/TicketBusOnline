package com.um.main.exceptions;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String string) {
        super("Resource "+string+" not found");
    }
}
