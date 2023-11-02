package com.um.main.exceptions;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(Long message) {
        super("Resource with id "+message+" not found");
    }
}
