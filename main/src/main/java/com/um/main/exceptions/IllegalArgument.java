package com.um.main.exceptions;

public class IllegalArgument extends RuntimeException{
    public IllegalArgument(String message) {
        super(message+" cannot be empty");
    }
}
