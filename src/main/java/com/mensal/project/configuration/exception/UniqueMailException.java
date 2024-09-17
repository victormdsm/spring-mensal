package com.mensal.project.configuration.exception;

public class UniqueMailException extends RuntimeException{
    public UniqueMailException(String message) {
        super(message);
    }
}
