package ru.brickly.core.exception;

public class MinifigNotFoundException extends RuntimeException {
    public MinifigNotFoundException(String message) {
        super(message);
    }
}
