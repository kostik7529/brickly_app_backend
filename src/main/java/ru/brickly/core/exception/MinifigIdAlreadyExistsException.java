package ru.brickly.core.exception;

public class MinifigIdAlreadyExistsException extends RuntimeException {
    public MinifigIdAlreadyExistsException(String message) {
        super(message);
    }
}
